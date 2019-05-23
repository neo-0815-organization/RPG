package rpg.worldcreator;

import static rpg.worldcreator.Data.tileSize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import rpg.Logger;
import rpg.api.gfx.ImageUtility;
import rpg.api.packethandler.ByteBuffer;
import rpg.worldcreator.dialogs.EditHitboxDialog;
import rpg.worldcreator.dialogs.NewMapDialog;

public class WorldCreatorFrame extends JFrame {
	private static final long serialVersionUID = -5092028467904882919L;
	
	private final WorldCreatorFrame INSTANCE = this;
	private final Export export = new Export();
	private final NewMapDialog newMapDialog = new NewMapDialog(INSTANCE);
	
	private final JPanel workingArea = new JPanel() {
		private static final long serialVersionUID = -1342757438932065165L;
		
		@Override
		public Dimension getPreferredSize() {
			if(spritePanes != null) return new Dimension(spritePanes[0][0].getWidth() * spritePanes.length, spritePanes[0][0].getWidth() * spritePanes[0].length);
			
			return super.getPreferredSize();
		}
	}, currentTextureShowPanel = new JPanel() {
		private static final long serialVersionUID = 3649405991511062410L;
		
		@Override
		protected void paintComponent(final Graphics g) {
			super.paintComponent(g);
			
			int newSize = getHeight();
			if(newSize > getWidth()) newSize = getWidth();
			
			newSize -= 10;
			
			if(!currentTexture.isNull()) g.drawImage(ImageUtility.scale(currentTexture.getImage(), newSize, newSize), 5, 5, null);
		}
	};
	private final JProgressBar progressBar = new JProgressBar();
	private final HashMap<String, Cursor> cursors = new HashMap<>();
	protected final ActionListener commandActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			final String command = e.getActionCommand();
			
			switch(command) {
				case "exit":
					System.out.println("EXIT -- CLOSED");
					
					dispose();
					break;
				case "new":
					newMapDialog.setVisible(true);
					
					final Dimension dimension = newMapDialog.getDimension();
					if(dimension != null) {
						spritePanes = new SpritePane[dimension.width][dimension.height];
						
						createSpritePanes();
						updateTitle("untitled.wc");
					}
					
					break;
				case "save":
					if(openedFile != null && openedFile.exists()) {
						saveFile();
						
						break;
					}
				case "save_as":
					final JFileChooser saveFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(""));
					saveFileChooser.setAcceptAllFileFilterUsed(false);
					saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("WORLD Files (*.wc)", "wc"));
					
					if(saveFileChooser.showSaveDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) {
						openedFile = Utility.addExtension(saveFileChooser.getSelectedFile(), (FileNameExtensionFilter) saveFileChooser.getFileFilter());
						
						saveFile();
					}
					
					break;
				case "export":
					final JFileChooser exportFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(""));
					exportFileChooser.setAcceptAllFileFilterUsed(false);
					exportFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files (*.png)", "png"));
					exportFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("WORLD Files (*.world)", "world"));
					
					if(exportFileChooser.showSaveDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) exportFile(Utility.addExtension(exportFileChooser.getSelectedFile(), (FileNameExtensionFilter) exportFileChooser.getFileFilter()));
					
					break;
				case "open":
					final JFileChooser openFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(""));
					openFileChooser.setAcceptAllFileFilterUsed(false);
					openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("WORLD Files (*.wc)", "wc"));
					
					if(openFileChooser.showOpenDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) openFile(openFileChooser.getSelectedFile());
					
					break;
				case "zoom":
					try {
						final String zoom = JOptionPane.showInputDialog("Change zoom", factor);
						
						if(zoom != null) applyZoom(Double.parseDouble(zoom));
					}catch(final NumberFormatException ex) {
						showError(ex);
					}
					
					break;
				case "clear":
					if(spritePanes != null) Arrays.stream(spritePanes).parallel().flatMap(Arrays::stream).forEach(pane -> {
						for(int i = 0; i < pane.images.length; i++)
							pane.setImage(i, null);
						
						for(int i = 0; i < pane.hitboxes.length; i++)
							pane.hitboxes[i] = WorldCreatorHitbox.nullBox;
					});
					
					break;
				case "refresh":
					currentTextureShowPanel.repaint();
					
					workingArea.revalidate();
					workingArea.repaint();
					
					break;
				case "update":
					updateFile(openedFile);
					break;
			}
			
			if(!command.equals("new") && !command.equals("exit")) updateProgressBar(0);
		}
	}, prefixActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			final String[] actionCommand = e.getActionCommand().split(":");
			final String prefix = actionCommand[0], command = actionCommand[1], argString = actionCommand.length == 3 ? actionCommand[2] : "";
			final String[] args = argString.split("\\|");
			
			switch(prefix) {
				case "cursor":
					workingArea.setCursor(cursors.get(command));
					
					break;
				case "texture":
					currentLayer = Integer.valueOf(args[2]);
					
					if(RPGWorldCreator.getImageMap(currentLayer).containsKey(command)) {
						currentTexture = new Image(RPGWorldCreator.getImageMap(currentLayer).getSecond(command), RPGWorldCreator.getImageMap(currentLayer).getFirst(command), Integer.valueOf(args[0]) * tileSize, Integer.valueOf(args[1]) * tileSize, Rotation.NONE, factor);
						
						if(!layersPane.getLayer(currentLayer).showedWarning.get(command)) {
							JOptionPane.showMessageDialog(INSTANCE, RPGWorldCreator.getTexts().get(command), "WARNING", JOptionPane.WARNING_MESSAGE);
							
							layersPane.getLayer(currentLayer).showedWarning.put(command, true);
						}
					}else currentTexture = Image.nullImage;
					
					currentTextureShowPanel.repaint();
					break;
			}
		}
	};
	
	private SpritePane[][] spritePanes;
	private LayersPane layersPane;
	private double factor = 1d;
	private Image currentTexture = Image.nullImage;
	private int currentLayer = 1;
	private File openedFile;
	
	public WorldCreatorFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(final WindowEvent e) {
				commandActionListener.actionPerformed(new ActionEvent(this, -1, "exit"));
			}
		});
		
		updateTitle(null);
		setIconImage(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			registerCursor("pencil", new Point(3, 28));
			registerCursor("eraser", new Point(6, 25));
			registerCursor("bucket", new Point(5, 21));
			registerCursor("rotate", new Point(16, 15));
			registerCursor("hitbox", new Point(5, 22));
			
			workingArea.setCursor(cursors.get("pencil"));
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | HeadlessException e) {
			Logger.error(e);
		}
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		setContentPane(new JPanel());
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBorder(new EmptyBorder(3, 3, 3, 3));
		if(RPGWorldCreator.isDarkmode()) getContentPane().setBackground(new Color(44, 49, 53));
		
		initMenuBar();
		initComponents();
	}
	
	private void initComponents() {
		final JPanel tools = new JPanel();
		tools.setLayout(new GridLayout(0, 1));
		tools.setBorder(new LineBorder(Color.DARK_GRAY));
		tools.setPreferredSize(new Dimension(150, 0));
		tools.add(new ToolPanel());
		add(tools, BorderLayout.WEST);
		
		workingArea.setLayout(null);
		workingArea.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(final MouseEvent e) {
				if(pressing) {
					pressing = false;
					button = 0;
					
					workingArea.repaint();
				}
			};
		});
		final JScrollPane scrollWorkingArea = new JScrollPane(workingArea);
		scrollWorkingArea.getVerticalScrollBar().setUnitIncrement(32);
		scrollWorkingArea.getHorizontalScrollBar().setUnitIncrement(32);
		add(scrollWorkingArea, BorderLayout.CENTER);
		
		layersPane = new LayersPane();
		add(layersPane, BorderLayout.EAST);
		
		progressBar.setForeground(Color.GREEN);
		add(progressBar, BorderLayout.SOUTH);
		
		if(RPGWorldCreator.isDarkmode()) {
			workingArea.setBackground(new Color(44, 49, 53));
			currentTextureShowPanel.setBackground(new Color(44, 49, 53));
			progressBar.setBackground(new Color(25, 29, 31));
		}
	}
	
	private void initMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		addMenuItem(fileMenu, "New...", KeyEvent.VK_N, KeyEvent.VK_N, "new");
		addMenuItem(fileMenu, "Open...", KeyEvent.VK_O, KeyEvent.VK_O, "open");
		addMenuItem(fileMenu, "Save...", KeyEvent.VK_S, KeyEvent.VK_S, "save");
		addMenuItem(fileMenu, "Save As...", KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, "save_as");
		addMenuItem(fileMenu, "Export...", KeyEvent.VK_E, KeyEvent.VK_E, "export");
		fileMenu.addSeparator();
		addMenuItem(fileMenu, "Update...", KeyEvent.VK_U, -1, "update");
		fileMenu.addSeparator();
		addMenuItem(fileMenu, "Exit...", KeyEvent.VK_X, KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK, "exit");
		menuBar.add(fileMenu);
		
		final JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		addMenuItem(editMenu, "Zoom...", KeyEvent.VK_Z, KeyEvent.VK_Z, "zoom");
		addMenuItem(editMenu, "Clear...", KeyEvent.VK_C, -1, "clear");
		editMenu.addSeparator();
		addMenuItem(editMenu, "Refresh", KeyEvent.VK_R, KeyEvent.VK_F5, "refresh");
		menuBar.add(editMenu);
		
		final JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		addMenuItem(aboutMenu, "Version: " + Data.version, -1, -1, null);
		menuBar.add(aboutMenu);
		
		setJMenuBar(menuBar);
	}
	
	private void addMenuItem(final JMenu menu, final String name, final int mnemonic, final int strokeKey, final String actionCommand) {
		addMenuItem(menu, name, mnemonic, strokeKey, KeyEvent.CTRL_DOWN_MASK, actionCommand);
	}
	
	private void addMenuItem(final JMenu menu, final String name, final int mnemonic, final int strokeKey, final int stroke, final String actionCommand) {
		final JMenuItem menuItem = new JMenuItem(name, mnemonic);
		
		if(strokeKey >= 0 && stroke >= 0) menuItem.setAccelerator(KeyStroke.getKeyStroke(strokeKey, stroke));
		
		menuItem.addActionListener(commandActionListener);
		menuItem.setActionCommand(actionCommand);
		
		menu.add(menuItem);
	}
	
	private int finishedThreads, numberTiles;
	private long time;
	
	public void saveFile() {
		try {
			finishedThreads = 0;
			numberTiles = 0;
			time = System.currentTimeMillis();
			
			final int widthTiles = spritePanes.length, heightTiles = spritePanes[0].length;
			
			progressBar.setMaximum(widthTiles * heightTiles);
			updateProgressBar(0);
			
			if(openedFile == null || !openedFile.exists()) openedFile.createNewFile();
			
			final FileOutputStream fos = new FileOutputStream(openedFile);
			final ByteBuffer buf = new ByteBuffer();
			final TriConsumer<String, Integer, BufferedImage> writeConsumer = new TriConsumer<String, Integer, BufferedImage>() {
				
				@Override
				public void accept(final String name, final Integer id, final BufferedImage image) {
					buf.writeInt(id);
					buf.writeString(name);
				}
			};
			
			// write image paths to opened file
			buf.writeInt(RPGWorldCreator.getLayerCount());
			for(int i = 1; i < RPGWorldCreator.getLayerCount(); i++) {
				buf.writeInt(RPGWorldCreator.getImageMap(i).size());
				RPGWorldCreator.getImageMap(i).forEach(writeConsumer);
			}
			
			buf.writeInt(RPGWorldCreator.getImageMap(0).size());
			RPGWorldCreator.getImageMap(0).forEach(writeConsumer);
			
			// write every panel to opened file
			buf.writeInt(spritePanes.length);
			buf.writeInt(spritePanes[0].length);
			
			SpritePane pane;
			for(final SpritePane[] spritePane : spritePanes)
				for(final SpritePane element : spritePane) {
					pane = element;
					
					for(int i = 0; i < RPGWorldCreator.getLayerCount(); i++) {
						//						buf.writeInt(pane.images[i].getId() != -1 ? pane.images[i].getId() : 127);
						buf.writeInt(pane.images[i].getId());
						buf.writeInt(pane.images[i].getXShift());
						buf.writeInt(pane.images[i].getYShift());
						buf.write(pane.images[i].getRotation().getId());
					}
					
					for(int i = 0; i < RPGWorldCreator.getLayerCount(); i++) {
						buf.writeBoolean(!pane.hitboxes[i].isNull());
						
						if(pane.hitboxes[i].isNull()) continue;
						
						buf.writeDouble(pane.hitboxes[i].getDimension().getWidth());
						buf.writeDouble(pane.hitboxes[i].getDimension().getHeight());
					}
					
					updateProgressBar(++numberTiles);
				}
			
			buf.writeToOutputStream(fos);
			fos.close();
			
			updateProgressBar(progressBar.getMaximum());
			updateTitle(openedFile.getName());
			
			JOptionPane.showMessageDialog(workingArea, "Saved to '" + openedFile.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Saved", JOptionPane.INFORMATION_MESSAGE);
		}catch(final IOException e) {
			showError(e);
		}
	}
	
	public void openFile(final File file) {
		try {
			finishedThreads = 0;
			numberTiles = 0;
			time = System.currentTimeMillis();
			
			progressBar.setMaximum(100);
			updateProgressBar(0);
			
			if(file == null) {
				JOptionPane.showMessageDialog(INSTANCE, "The selected file is 'null'!!", "Open File", JOptionPane.ERROR_MESSAGE);
				
				return;
			}else openedFile = file;
			
			Arrays.stream(workingArea.getComponents()).parallel().filter(comp -> comp instanceof SpritePane).forEach(comp -> workingArea.remove(comp));
			
			final ByteBuffer buf = new ByteBuffer();
			final FileInputStream fis = new FileInputStream(file);
			
			buf.readFromInputStream(fis);
			
			final int layerCount = buf.readInt();
			int imageCount;
			for(int i = 1; i < layerCount; i++) {
				RPGWorldCreator.getImageMap(i).clear();
				imageCount = buf.readInt();
				
				for(int j = 0; j < imageCount; j++) {
					final int id = buf.readInt();
					final String name = buf.readString();
					
					RPGWorldCreator.getImageMap(i).put(name, id, RPGWorldCreator.getImage(RPGWorldCreator.assetsFolder, RPGWorldCreator.getMapDir(i) + "/" + name + ".png"));
				}
			}
			
			RPGWorldCreator.getImageMap(0).clear();
			imageCount = buf.readInt();
			for(int i = 0; i < imageCount; i++) {
				final int id = buf.readInt();
				final String name = buf.readString();
				
				RPGWorldCreator.getImageMap(0).put(name, id, RPGWorldCreator.getImage(RPGWorldCreator.assetsFolder, RPGWorldCreator.getMapDir(0) + "/" + name + ".png"));
			}
			
			// read every panel from file
			spritePanes = new SpritePane[buf.readInt()][buf.readInt()];
			
			final int paneSize = (int) (tileSize * factor);
			
			SpritePane pane;
			int id;
			boolean type;
			for(int x = 0; x < spritePanes.length; x++)
				for(int y = 0; y < spritePanes[x].length; y++) {
					pane = new SpritePane(x, y);
					pane.setBounds(x * paneSize, y * paneSize, paneSize, paneSize);
					
					for(int i = 0; i < layerCount; i++) {
						id = buf.readInt();
						
						//						if(id == 127) id = -1;
						
						pane.setImage(i, new Image(RPGWorldCreator.getImageMap(i).getSecond(RPGWorldCreator.getImageMap(i).keyWithValueOne(id)), id, buf.readInt(), buf.readInt(), Rotation.getById(buf.read()), factor));
					}
					
					for(int i = 0; i < layerCount; i++) {
						type = buf.readBoolean();
						
						if(type) {
							pane.hitboxes[i].setType(false);
							pane.hitboxes[i].getDimension().setSize(buf.readDouble(), buf.readDouble());
						}
					}
					
					workingArea.add(pane);
					spritePanes[x][y] = pane;
					
					updateProgressBar(++numberTiles);
				}
			
			fis.close();
			
			updateProgressBar(progressBar.getMaximum());
			updateTitle(file.getName());
			
			workingArea.revalidate();
			workingArea.repaint();
			
			JOptionPane.showMessageDialog(workingArea, "Opened file '" + file.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Opened", JOptionPane.INFORMATION_MESSAGE);
		}catch(final IOException e) {
			showError(e);
		}
	}
	
	public void exportFile(final File file) {
		finishedThreads = 0;
		numberTiles = 0;
		time = System.currentTimeMillis();
		
		final int widthTiles = spritePanes.length, heightTiles = spritePanes[0].length;
		
		progressBar.setMaximum(widthTiles * heightTiles);
		updateProgressBar(0);
		
		if(file == null || !file.exists()) try {
			file.createNewFile();
		}catch(final IOException e) {
			Logger.error(e);
		}
		
		try {
			final String extension = file.getAbsolutePath().split("\\.")[file.getAbsolutePath().split("\\.").length - 1];
			switch(extension) {
				case "world":
					export.exportZIP(file);
					break;
				case "png":
					final FileOutputStream fos = new FileOutputStream(file);
					
					export.exportPNG(fos);
					
					fos.close();
					break;
			}
		}catch(final IOException e) {
			showError(e);
		}
		
		JOptionPane.showMessageDialog(workingArea, "Exported to '" + file.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Exported", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private class Export {
		
		private void exportZIP(final File file) {
			try {
				final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file));
				
				zos.putNextEntry(new ZipEntry("background"));
				exportPNG(zos);
				zos.closeEntry();
				
				zos.putNextEntry(new ZipEntry("mapping"));
				exportMapping(zos);
				zos.closeEntry();
				
				zos.putNextEntry(new ZipEntry("tiles"));
				exportTiles(zos);
				zos.closeEntry();
				
				zos.putNextEntry(new ZipEntry("fluids"));
				exportFluids(zos);
				zos.closeEntry();
				
				zos.putNextEntry(new ZipEntry("hitboxes"));
				exportHitboxes(zos);
				zos.closeEntry();
				
				zos.close();
			}catch(final IOException e) {
				showError(e);
			}
		}
		
		private void exportPNG(final OutputStream os) {
			try {
				final int widthTiles = spritePanes.length, heightTiles = spritePanes[0].length;
				final BufferedImage image = new BufferedImage(widthTiles * tileSize, heightTiles * tileSize, BufferedImage.TYPE_INT_ARGB);
				final Graphics2D g = image.createGraphics();
				
				for(int x = 0; x < spritePanes.length; x++)
					new Thread("ExportThread-" + x) {
						
						private final int x = Integer.valueOf(getName().replace("ExportThread-", ""));
						
						@Override
						public void run() {
							for(int y = 0; y < spritePanes[x].length; y++) {
								g.drawImage(spritePanes[x][y].images[1].getImage(), x * tileSize, y * tileSize, tileSize, tileSize, null);
								
								numberTiles++;
							}
							
							finishedThreads++;
						};
					}.start();
				
				while(true) {
					updateProgressBar(numberTiles);
					
					if(finishedThreads == widthTiles) {
						updateProgressBar(progressBar.getMaximum());
						
						break;
					}
				}
				
				g.dispose();
				
				ImageIO.write(image, "PNG", os);
			}catch(final IOException e) {
				showError(e);
			}
		}
		
		private void exportMapping(final OutputStream os) {
			final ByteBuffer buf = new ByteBuffer();
			final TriConsumer<String, Integer, BufferedImage> writeConsumer = new TriConsumer<String, Integer, BufferedImage>() {
				
				@Override
				public void accept(final String name, final Integer id, final BufferedImage image) {
					buf.writeInt(id);
					buf.writeString(name);
				}
			};
			
			buf.writeInt(RPGWorldCreator.getMappingIndeces().length);
			for(final int i : RPGWorldCreator.getMappingIndeces()) {
				buf.writeInt(i);
				buf.writeInt(RPGWorldCreator.getImageMap(i).size());
				RPGWorldCreator.getImageMap(i).forEach(writeConsumer);
			}
			
			try {
				buf.writeToOutputStream(os);
			}catch(final IOException e) {
				showError(e);
			}
		}
		
		private void exportFluids(final OutputStream os) {
			final ByteBuffer buf = new ByteBuffer();
			final ArrayList<SpritePane> panes = new ArrayList<>();
			
			Arrays.stream(spritePanes).parallel().flatMap(Arrays::stream).filter(pane -> !pane.images[0].isNull()).forEach(panes::add);
			
			buf.writeInt(panes.size());
			panes.stream().forEach(pane -> {
				buf.writeInt(pane.paneX);
				buf.writeInt(pane.paneY);
				buf.writeInt(pane.images[0].getId());
			});
			
			try {
				buf.writeToOutputStream(os);
			}catch(final IOException e) {
				showError(e);
			}
		}
		
		private void exportTiles(final OutputStream os) {
			final ByteBuffer buf = new ByteBuffer();
			final ArrayList<SpritePane> panes = new ArrayList<>();
			
			Arrays.stream(spritePanes).parallel().flatMap(Arrays::stream).filter(pane -> !pane.images[2].isNull()).forEach(panes::add);
			
			buf.writeInt(panes.size());
			panes.stream().forEach(pane -> {
				buf.writeInt(pane.paneX);
				buf.writeInt(pane.paneY);
				buf.writeInt(pane.images[2].getId());
			});
			
			try {
				buf.writeToOutputStream(os);
			}catch(final IOException e) {
				showError(e);
			}
		}
		
		private void exportHitboxes(final OutputStream os) {
			final ByteBuffer buf = new ByteBuffer();
			final ArrayList<SpritePane> panes = new ArrayList<>();
			
			Arrays.stream(spritePanes).parallel().flatMap(Arrays::stream).filter(pane -> {
				for(final WorldCreatorHitbox hitbox : pane.hitboxes)
					if(!hitbox.isNull()) return true;
				
				return false;
			}).forEach(panes::add);
			
			buf.writeInt(panes.size());
			panes.stream().forEach(pane -> {
				buf.writeInt(pane.paneX);
				buf.writeInt(pane.paneY);
				
				int hitboxCount = 0;
				for(final WorldCreatorHitbox hitbox : pane.hitboxes)
					if(!hitbox.isNull()) hitboxCount++;
				buf.writeInt(hitboxCount);
				
				for(int i = 0; i < pane.hitboxes.length; i++) {
					if(pane.hitboxes[i].isNull()) continue;
					
					buf.writeInt(i);
					buf.writeDouble(pane.hitboxes[i].getDimension().getWidth());
					buf.writeDouble(pane.hitboxes[i].getDimension().getHeight());
				}
			});
			
			try {
				buf.writeToOutputStream(os);
			}catch(final IOException e) {
				showError(e);
			}
		}
	}
	
	private void updateFile(final File file) {
		time = System.currentTimeMillis();
		
		progressBar.setMaximum(100);
		updateProgressBar(0);
		
		final ArrayList<TwoValueMap<String, Integer, BufferedImage>> maps = new ArrayList<>();
		for(int i = 0; i < RPGWorldCreator.getLayerCount(); i++) {
			maps.add(RPGWorldCreator.getImageMap(i).copy());
			
			RPGWorldCreator.getImageMap(i).clear();
			
			final HashMap<String, Integer> ids = new HashMap<>();
			maps.get(i).forEach((name, id, image) -> ids.put(name, id));
			
			RPGWorldCreator.loadPictures(RPGWorldCreator.getMapDir(i), RPGWorldCreator.getImageMap(i), ids);
		}
		
		updateProgressBar(progressBar.getMaximum());
		
		workingArea.revalidate();
		workingArea.repaint();
		
		JOptionPane.showMessageDialog(workingArea, "Updated file '" + file.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Updated", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void createSpritePanes() {
		finishedThreads = 0;
		numberTiles = 0;
		time = System.currentTimeMillis();
		
		final int size = (int) (tileSize * factor), widthTiles = spritePanes.length, heightTiles = spritePanes[0].length;
		
		progressBar.setMaximum(widthTiles * heightTiles);
		updateProgressBar(0);
		
		Arrays.stream(workingArea.getComponents()).parallel().filter(comp -> comp instanceof SpritePane).forEach(comp -> workingArea.remove(comp));
		
		for(int x = 0; x < widthTiles; x++)
			new Thread("CreateThread-" + x) {
				private final int x = Integer.valueOf(getName().replace("CreateThread-", ""));
				private SpritePane paneToAdd;
				
				@Override
				public void run() {
					for(int y = 0; y < heightTiles; y++) {
						paneToAdd = new SpritePane(x, y);
						paneToAdd.setBounds(x * size, y * size, size, size);
						
						workingArea.add(paneToAdd);
						spritePanes[x][y] = paneToAdd;
						
						numberTiles++;
					}
					
					finishedThreads++;
				}
			}.start();
		
		while(true) {
			updateProgressBar(numberTiles);
			
			if(finishedThreads == widthTiles) {
				updateProgressBar(progressBar.getMaximum());
				
				break;
			}
		}
		
		workingArea.setPreferredSize(new Dimension(widthTiles * size, heightTiles * size));
		
		workingArea.revalidate();
		workingArea.repaint();
		
		JOptionPane.showMessageDialog(workingArea, "Created " + widthTiles + "x" + heightTiles + " (" + (System.currentTimeMillis() - time) + " ms)", "Created", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void applyZoom(final double factor) {
		Arrays.stream(spritePanes).parallel().flatMap(array -> Arrays.stream(array)).forEach(pane -> pane.setScaleFactor(factor));
		
		this.factor = factor;
		
		currentTexture.setScaleFactor(factor);
		
		workingArea.revalidate();
		workingArea.repaint();
	}
	
	private class ToolPanel extends JPanel {
		
		private static final long serialVersionUID = -333048293545148426L;
		
		private final ButtonGroup buttonGroup = new ButtonGroup();
		
		public ToolPanel() {
			setLayout(new GridLayout(6, 1));
			setBorder(new EmptyBorder(3, 3, 3, 3));
			
			if(RPGWorldCreator.isDarkmode()) setBackground(new Color(25, 29, 31));
			
			initComponents();
		}
		
		private void initComponents() {
			buttonGroup.add(new JToggleButton("Pencil", true));
			buttonGroup.add(new JToggleButton("Eraser"));
			buttonGroup.add(new JToggleButton("Bucket"));
			buttonGroup.add(new JToggleButton("Rotate"));
			buttonGroup.add(new JToggleButton("Hitbox"));
			
			final Enumeration<AbstractButton> buttons = buttonGroup.getElements();
			AbstractButton button = null;
			while(buttons.hasMoreElements()) {
				button = buttons.nextElement();
				
				button.setIcon(new ImageIcon(RPGWorldCreator.getImage("assets/worldcreator/cursors/" + button.getText().toLowerCase() + ".png")));
				button.addActionListener(prefixActionListener);
				button.setActionCommand("cursor:" + button.getText().toLowerCase());
				button.setFocusPainted(false);
				add(button);
			}
			
			currentTextureShowPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			add(currentTextureShowPanel);
		}
	}
	
	private class LayersPane extends JTabbedPane {
		private static final long serialVersionUID = 6575636653675456721L;
		
		private HashMap<Integer, LayerPanel> layerPanels = new HashMap<>();
		
		public LayersPane() {
			initComponents();
		}
		
		private void initComponents() {
			addLayer(RPGWorldCreator.getTextures(), 1, "Textures");
			addLayer(RPGWorldCreator.getTiles(), 2, "Tiles");
			addLayer(RPGWorldCreator.getFluids(), 0, "Fluids");
		}
		
		private void addLayer(final TwoValueMap<String, Integer, BufferedImage> pictures, final int layer, final String tabName) {
			final JScrollPane pane = new JScrollPane(new LayerPanel(pictures, layer, INSTANCE));
			pane.setPreferredSize(new Dimension(150, 0));
			pane.setBorder(new LineBorder(Color.DARK_GRAY));
			pane.getVerticalScrollBar().setUnitIncrement(32);
			
			add(tabName, pane);
			layerPanels.put(layer, (LayerPanel) pane.getViewport().getView());
		}
		
		public LayerPanel getLayer(int layer) {
			return layerPanels.get(layer);
		}
	}
	
	private boolean pressing = false;
	private int button = 0;
	
	public class SpritePane extends JPanel {
		private static final long serialVersionUID = -2548204979994837953L;
		private final SpritePane paneInstance = this;
		private final MouseListener tilePaneMouseListener = new MouseAdapter() {
			
			@Override
			public void mousePressed(final MouseEvent e) {
				if(!pressing) {
					pressing = true;
					button = e.getButton();
					
					mouseEntered(e);
				}
			};
			
			@Override
			public void mouseReleased(final MouseEvent e) {
				if(pressing) {
					pressing = false;
					button = 0;
					
					workingArea.repaint();
				}
			};
			
			@Override
			public void mouseEntered(final MouseEvent e) {
				if(pressing) switch(getCursor().getName()) {
					case "pencil":
						if(button == 1) setImage(currentTexture.copy());
						else if(button == 3) setImage(null);
						
						break;
					case "eraser":
						if(button == 1 || button == 3) setImage(null);
						
						break;
					case "bucket":
						if(button == 1) bucketFill(images[currentLayer], currentTexture);
						else if(button == 3) bucketFill(images[currentLayer], null);
						
						break;
					case "rotate":
						if(images[0] != null) if(button == 1) setRotated(1);
						else if(button == 3) setRotated(-1);
						
						break;
					case "hitbox":
						if(button == 1) {
							pressing = false;
							
							final EditHitboxDialog hitboxDialog = new EditHitboxDialog(INSTANCE, paneInstance);
							hitboxDialog.setVisible(true);
							
							hitboxes[0] = hitboxDialog.getHitbox(0);
							hitboxes[0].setScale(factor);
							
							hitboxes[2] = hitboxDialog.getHitbox(2);
							hitboxes[2].setScale(factor);
						}else if(button == 3) hitboxes[2] = WorldCreatorHitbox.nullBox;
						
						repaint();
						break;
				}
			};
		};
		
		private void bucketFill(final Image imageToReplace, final Image newImage) {
			final HashMap<SpritePane, Integer> tilesToFill = new HashMap<>();
			
			bucketFill(tilesToFill, imageToReplace, newImage);
			
			tilesToFill.keySet().parallelStream().forEach(pane -> {
				if(newImage != null) pane.setImage(newImage.copy());
				else pane.setImage(null);
			});
		}
		
		private void bucketFill(final HashMap<SpritePane, Integer> tilesToFill, final Image imageToReplace, final Image newImage) {
			if(tilesToFill.containsKey(this)) return;
			
			if(images[currentLayer].equals(imageToReplace)) {
				tilesToFill.put(this, 0);
				
				SpritePane nextPane;
				int newX, newY;
				for(final Point point : Data.fillOffsets) {
					newX = paneX + point.x;
					newY = paneY + point.y;
					
					if(newX < 0 || newX >= spritePanes.length || newY < 0 || newY >= spritePanes[0].length) continue;
					
					nextPane = spritePanes[newX][newY];
					
					if(nextPane.images[currentLayer].equals(imageToReplace)) nextPane.bucketFill(tilesToFill, imageToReplace, newImage);
				}
			}
		}
		
		private final Image[] images = new Image[3];
		private final int paneX, paneY;
		private final WorldCreatorHitbox[] hitboxes = new WorldCreatorHitbox[3];
		
		private SpritePane(final int paneX, final int paneY) {
			this.paneX = paneX;
			this.paneY = paneY;
			
			for(int i = 0; i < images.length; i++)
				images[i] = Image.nullImage;
			
			for(int i = 0; i < hitboxes.length; i++)
				hitboxes[i] = WorldCreatorHitbox.nullBox.copy();
			
			setLayout(null);
			setBackground(new Color(199, 199, 199));
			setBorder(new LineBorder(Color.LIGHT_GRAY));
			addMouseListener(tilePaneMouseListener);
		}
		
		@Override
		public void paintComponent(final Graphics g) {
			super.paintComponent(g);
			
			for(final Image image : images)
				if(image != null && image.getImage() != null) g.drawImage(image.getImage(), 0, 0, null);
			
			for(final WorldCreatorHitbox hitbox : hitboxes)
				hitbox.draw(g);
		}
		
		public void setImage(final Image image) {
			setImage(currentLayer, image);
		}
		
		public void setImage(final int layer, Image image) {
			if(image == null) image = Image.nullImage;
			
			images[layer] = image;
			
			repaint();
		}
		
		public void setRotated(final int direction) {
			setRotation(images[currentLayer].getRotation().rotate(direction));
		}
		
		public void setRotation(final Rotation rotation) {
			images[currentLayer].setRotation(rotation);
			
			repaint();
		}
		
		public void setScaleFactor(final double scaleFactor) {
			Arrays.stream(images).parallel().forEach(image -> image.setScaleFactor(scaleFactor));
			Arrays.stream(hitboxes).parallel().forEach(hitbox -> hitbox.setScale(scaleFactor));
			
			final int size = (int) (tileSize * scaleFactor);
			
			setBounds(paneX * size, paneY * size, size, size);
		}
		
		public BufferedImage getImages() {
			int id = -1;
			
			for(int i = 0; i < images.length; i++)
				if(!images[i].isNull()) id = i;
			
			if(id == -1) return null;
			
			final BufferedImage templateImage = images[id].getImage(), result = new BufferedImage(templateImage.getWidth(), templateImage.getHeight(), templateImage.getType());
			final Graphics g = result.getGraphics();
			
			for(final Image image : images)
				if(image != null && image.getImage() != null) g.drawImage(image.getImage(), 0, 0, null);
			
			return result;
		}
		
		public WorldCreatorHitbox getHitboxCopy(final int i) {
			return hitboxes[i].copy();
		}
	}
	
	protected void updateTitle(final String filename) {
		setTitle("RPG WorldCreator" + (filename == null || filename.isEmpty() ? "" : " -- \"" + filename + "\""));
	}
	
	protected void updateProgressBar(final int value) {
		if(value >= 0) progressBar.setValue(value);
		
		progressBar.update(progressBar.getGraphics());
	}
	
	private void registerCursor(final String name, final Point hotSpot) {
		registerCursor(name, hotSpot, getToolkit());
	}
	
	private void registerCursor(final String name, final Point hotSpot, final Toolkit toolkit) {
		cursors.put(name, toolkit.createCustomCursor(RPGWorldCreator.getImage("assets/worldcreator/cursors/" + name + ".png"), hotSpot, name));
	}
	
	private void showError(final Exception e) {
		showError(e.getClass().getName() + "\n\n" + e.getMessage());
	}
	
	private void showError(final String error) {
		JOptionPane.showMessageDialog(workingArea, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public JPanel getContentPane() {
		return (JPanel) super.getContentPane();
	}
}
