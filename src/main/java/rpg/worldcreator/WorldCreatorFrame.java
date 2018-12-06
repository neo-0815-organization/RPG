package rpg.worldcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

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
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import rpg.worldcreator.dialogs.NewMapDialog;

public class WorldCreatorFrame extends JFrame {
	private static final long serialVersionUID = -5092028467904882919L;
	
	private final WorldCreatorFrame INSTANCE = this;
	private final NewMapDialog newMapDialog = new NewMapDialog(INSTANCE);
	
	private final JPanel workingArea = new JPanel();
	private final JProgressBar progressBar = new JProgressBar();
	private final HashMap<String, Cursor> cursors = new HashMap<>();
	private final ActionListener commandActionListener = new ActionListener() {
		
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
						texturePanes = new TexturePane[dimension.width][dimension.height];
						
						updateTitle("untitled.world");
						updateTexturePanes();
					}
					
					break;
				case "save":
					if(openedFile != null && openedFile.exists()) {
						saveFile();
						
						break;
					}
					
					final JFileChooser saveFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(getClass().getResource("/").getFile()));
					saveFileChooser.setAcceptAllFileFilterUsed(false);
					saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("WORLD Files (*.world)", "world"));
					
					if(saveFileChooser.showSaveDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) {
						openedFile = saveFileChooser.getSelectedFile();
						
						saveFile();
					}
					
					break;
				case "export":
					final JFileChooser exportFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(getClass().getResource("/").getFile()));
					exportFileChooser.setAcceptAllFileFilterUsed(false);
					exportFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files (*.png)", "png"));
					
					if(exportFileChooser.showSaveDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) exportFile(exportFileChooser.getSelectedFile());
					
					break;
				case "open":
					final JFileChooser openFileChooser = new JFileChooser(openedFile != null ? openedFile.getParentFile() : new File(getClass().getResource("/").getFile()));
					openFileChooser.setAcceptAllFileFilterUsed(false);
					openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("WORLD Files (*.world)", "world"));
					
					if(openFileChooser.showOpenDialog(INSTANCE) == JFileChooser.APPROVE_OPTION) {
						openFile(openFileChooser.getSelectedFile());
					}
					
					break;
				case "size":
					break;
				case "clear":
					if(texturePanes != null) Arrays.stream(texturePanes).parallel().flatMap(array -> Arrays.stream(array)).forEach(pane -> pane.setImage(null));
					
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
				case "sprite":
					currentTexture = RPGWorldCreator.getTextures().getSecond(command);
					currentTextureId = RPGWorldCreator.getTextures().getFirst(command);
					currentTextureX = Integer.valueOf(args[0]);
					currentTextureY = Integer.valueOf(args[1]);
					
					break;
			}
		}
	};
	
	private TexturePane[][] texturePanes;
	private final double factor = 1d;
	private BufferedImage currentTexture;
	private int currentTextureId, currentTextureX, currentTextureY;
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
			
			registerCursor("pencil", new Point(4, 27));
			registerCursor("eraser", new Point(5, 26));
			registerCursor("bucket", new Point(1, 20));
			registerCursor("rotate", new Point(16, 12));
			
			workingArea.setCursor(cursors.get("pencil"));
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | HeadlessException e) {
			e.printStackTrace();
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
		add(new JScrollPane(workingArea), BorderLayout.CENTER);
		
		final JScrollPane sprites = new JScrollPane(new SpritesPanel());
		sprites.setPreferredSize(new Dimension(150, 0));
		sprites.setBorder(new LineBorder(Color.DARK_GRAY));
		add(sprites, BorderLayout.EAST);
		
		progressBar.setForeground(Color.GREEN);
		if(RPGWorldCreator.isDarkmode()) progressBar.setBackground(new Color(25, 29, 31));
		add(progressBar, BorderLayout.SOUTH);
	}
	
	private void initMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		
		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		final JMenuItem newItem = new JMenuItem("New...", KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		newItem.addActionListener(commandActionListener);
		newItem.setActionCommand("new");
		fileMenu.add(newItem);
		final JMenuItem openItem = new JMenuItem("Open...", KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		openItem.addActionListener(commandActionListener);
		openItem.setActionCommand("open");
		fileMenu.add(openItem);
		final JMenuItem saveItem = new JMenuItem("Save...", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		saveItem.addActionListener(commandActionListener);
		saveItem.setActionCommand("save");
		fileMenu.add(saveItem);
		final JMenuItem exportItem = new JMenuItem("Export...", KeyEvent.VK_E);
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
		exportItem.addActionListener(commandActionListener);
		exportItem.setActionCommand("export");
		fileMenu.add(exportItem);
		fileMenu.addSeparator();
		final JMenuItem exitItem = new JMenuItem("Exit...", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		exitItem.addActionListener(commandActionListener);
		exitItem.setActionCommand("exit");
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		
		final JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		final JMenuItem sizeItem = new JMenuItem("Size...", KeyEvent.VK_S);
		sizeItem.addActionListener(commandActionListener);
		sizeItem.setActionCommand("size");
		editMenu.add(sizeItem);
		final JMenuItem clearItem = new JMenuItem("Clear...", KeyEvent.VK_C);
		clearItem.addActionListener(commandActionListener);
		clearItem.setActionCommand("clear");
		editMenu.add(clearItem);
		menuBar.add(editMenu);
		
		setJMenuBar(menuBar);
	}
	
	private int finishedThreads, numberTiles;
	private long time;
	
	public void saveFile() {
		try {			
			finishedThreads = 0;
			numberTiles = 0;
			time = System.currentTimeMillis();
			
			final int size = (int) (32 * factor), widthTiles = texturePanes.length, heightTiles = texturePanes[0].length;
			
			progressBar.setMaximum(widthTiles * heightTiles);
			updateProgressBar(0);
			
			if(openedFile == null || !openedFile.exists()) {
				openedFile.createNewFile();
			}
			
			final FileWriter writer = new FileWriter(openedFile);
			
			// write texture paths to opened file
			writer.write(RPGWorldCreator.getTextures().size());
			RPGWorldCreator.getTextures().forEach((name, id, image) -> {
				try {
					writer.write(id);
					writer.write(name.length());
					writer.write(name.toCharArray());
				}catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			// write every panel to opened file
			writer.write(texturePanes[0].length);
			writer.write(texturePanes.length);
			
			TexturePane pane;
			for(int x = 0; x < texturePanes.length; x++)
				for(int y = 0; y < texturePanes[x].length; y++) {
					pane = texturePanes[x][y];
					
					writer.write(pane.imageId);
					writer.write(pane.xShift);
					writer.write(pane.yShift);
					writer.write(pane.rotation);
					
					updateProgressBar(++numberTiles);
				}
			
			writer.close();
			
			updateProgressBar(progressBar.getMaximum());
			
			JOptionPane.showMessageDialog(workingArea, "Saved to '" + openedFile.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Saved", JOptionPane.INFORMATION_MESSAGE);
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openFile(final File file) {
		finishedThreads = 0;
		numberTiles = 0;
		time = System.currentTimeMillis();
		
		progressBar.setMaximum(100);
		updateProgressBar(0);
		
		if(file == null) {
			JOptionPane.showMessageDialog(INSTANCE, "The selected file is 'null'!!", "Open File", JOptionPane.ERROR_MESSAGE);
			
			return;
		}else {
			openedFile = file;
		}
		
		Arrays.stream(workingArea.getComponents()).parallel().filter(comp -> comp instanceof TexturePane).forEach(comp -> workingArea.remove(comp));
		
		try {			
			final BufferedReader reader = new BufferedReader(new FileReader(file));
			
			// read texture paths from opened file
			RPGWorldCreator.getTextures().clear();
			int size = reader.read();
			for(int i = 0; i < size; i++) {
				final int id = reader.read();
				final char[] name = new char[reader.read()];
				reader.read(name);
				
				RPGWorldCreator.getTextures().put(String.copyValueOf(name), id, null);
			}
			
			// read every panel from file
			texturePanes = new TexturePane[reader.read()][reader.read()];
			
			TexturePane pane;
			for(int x = 0; x < texturePanes.length; x++)
				for(int y = 0; y < texturePanes[x].length; y++) {
					pane = new TexturePane();
					pane.setBounds(x * size, y * size, size, size);
					
					pane.imageId = reader.read();
					pane.setImage(RPGWorldCreator.getTextures().getSecond(RPGWorldCreator.getTextures().keyWithValueOne(pane.imageId)), reader.read(), reader.read());
					pane.setRotated(reader.read());
					
					workingArea.add(pane);
					texturePanes[x][y] = pane;
					
					updateProgressBar(++numberTiles);
				}
			
			reader.close();
			
			updateProgressBar(progressBar.getMaximum());
			updateTitle(openedFile.getName());
			
			JOptionPane.showMessageDialog(workingArea, "Opened file '" + openedFile.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Opened", JOptionPane.INFORMATION_MESSAGE);
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportFile(final File file) {
		finishedThreads = 0;
		numberTiles = 0;
		time = System.currentTimeMillis();
		
		final int size = (int) (32 * factor), widthTiles = texturePanes.length, heightTiles = texturePanes[0].length;
		
		progressBar.setMaximum(widthTiles * heightTiles);
		updateProgressBar(0);
		
		if(file == null || !file.exists()) try {
			file.createNewFile();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		try {
			final BufferedImage image = new BufferedImage(widthTiles * size, heightTiles * size, BufferedImage.TYPE_INT_ARGB);
			final Graphics2D g = image.createGraphics();
			
			for(int x = 0; x < texturePanes.length; x++)
				new Thread("ExportThread-" + x) {
					private final int x = Integer.valueOf(getName().replace("ExportThread-", ""));
					
					@Override
					public void run() {
						for(int y = 0; y < texturePanes[x].length; y++) {
							g.drawImage(texturePanes[x][y].image, x * 32, y * 32, 32, 32, null);
							
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
			
			ImageIO.write(image, "PNG", file);
			
			JOptionPane.showMessageDialog(workingArea, "Exported to '" + file.getAbsolutePath() + "' (" + (System.currentTimeMillis() - time) + " ms)", "Exported", JOptionPane.INFORMATION_MESSAGE);
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateTexturePanes() {
		finishedThreads = 0;
		numberTiles = 0;
		time = System.currentTimeMillis();
		
		final int size = (int) (32 * factor), widthTiles = texturePanes.length, heightTiles = texturePanes[0].length;
		
		progressBar.setMaximum(widthTiles * heightTiles);
		updateProgressBar(0);
		
		Arrays.stream(workingArea.getComponents()).parallel().filter(comp -> comp instanceof TexturePane).forEach(comp -> workingArea.remove(comp));
		
		for(int x = 0; x < widthTiles; x++)
			new Thread("CreateThread-" + x) {
				private final int x = Integer.valueOf(getName().replace("CreateThread-", ""));
				private TexturePane paneToAdd;
				
				@Override
				public void run() {
					for(int y = 0; y < heightTiles; y++) {
						paneToAdd = new TexturePane();
						paneToAdd.setBounds(x * size, y * size, size, size);
						
						workingArea.add(paneToAdd);
						texturePanes[x][y] = paneToAdd;
						
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
	
	private class ToolPanel extends JPanel {
		private static final long serialVersionUID = -333048293545148426L;
		
		private final ButtonGroup buttonGroup = new ButtonGroup();
		
		public ToolPanel() {
			setLayout(new GridLayout(4, 1));
			setBorder(new EmptyBorder(3, 3, 3, 3));
			
			if(RPGWorldCreator.isDarkmode()) setBackground(new Color(25, 29, 31));
			
			initComponents();
		}
		
		private void initComponents() {
			buttonGroup.add(new JToggleButton("Pencil", true));
			buttonGroup.add(new JToggleButton("Eraser"));
			buttonGroup.add(new JToggleButton("Bucket"));
			buttonGroup.add(new JToggleButton("Rotate"));
			
			final Enumeration<AbstractButton> buttons = buttonGroup.getElements();
			AbstractButton button = null;
			while(buttons.hasMoreElements()) {
				button = buttons.nextElement();
				
				button.setIcon(new ImageIcon(RPGWorldCreator.getImage("/assets/worldcreator/cursors/" + button.getText().toLowerCase() + ".png")));
				button.addActionListener(prefixActionListener);
				button.setActionCommand("cursor:" + button.getText().toLowerCase());
				button.setFocusPainted(false);
				add(button);
			}
		}
	}
	
	private class SpritesPanel extends JPanel {
		private static final long serialVersionUID = -8842399534328251555L;
		
		private final ButtonGroup buttonGroup = new ButtonGroup();
		
		public SpritesPanel() {
			setLayout(new GridLayout(10, 3));
			setBorder(new EmptyBorder(3, 3, 3, 3));
			
			if(RPGWorldCreator.isDarkmode()) setBackground(new Color(25, 29, 31));
			
			initComponents();
		}
		
		private void initComponents() {
			RPGWorldCreator.getTextures().forEach((name, id, image) -> {
				final int xNumber = image.getWidth() / 32, yNumber = image.getHeight() / 32;
				
				for(int x = 0; x < xNumber; x++)
					for(int y = 0; y < yNumber; y++) {
						final JToggleButton button = new JToggleButton(new ImageIcon(image.getSubimage(x * 32, y * 32, 32, 32)));
						button.addActionListener(prefixActionListener);
						button.setActionCommand("sprite:" + name + ":" + x * 32 + "|" + y * 32);
						button.setFocusPainted(false);
						
						buttonGroup.add(button);
						add(button);
					}
			});
		}
	}
	
	private boolean pressing = false;
	private int button = 0;
	
	private class TexturePane extends JPanel {
		private static final long serialVersionUID = -2548204979994837953L;
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
						if(button == 1) setImage(currentTexture, currentTextureX, currentTextureY);
						else if(button == 3) setImage(null);
						
						break;
					case "eraser":
						if(button == 1 || button == 3) setImage(null);
						
						break;
					case "bucket":
						if(button == 1) bucketFill(null, -1, -1, image, currentTexture); // TODO add coordinates
						else if(button == 3) bucketFill(null, -1, -1, image, null); // TODO add coordinates
						
						break;
					case "rotate":
						if(image != null) if(button == 1) setRotated(90);
						else if(button == 3) setRotated(-90);
						
						break;
				}
			};
		};
		
		private void bucketFill(TexturePane pane, int paneX, int paneY, BufferedImage image, BufferedImage newImage) {
			if(pane == null) pane = this;
			
			if(pane.image.equals(image)) {
				if(newImage != null) pane.setImage(newImage, currentTextureX, currentTextureY);
				else pane.setImage(null);
				
				int paneXPlus = paneX, paneXMinus = paneX, paneYPlus = paneY, paneYMinus = paneY;
				if((paneX + 1) < texturePanes.length) paneXPlus++;
				if((paneX - 1) < texturePanes.length) paneXMinus--;
				if((paneY + 1) < texturePanes.length) paneYPlus++;
				if((paneY - 1) < texturePanes.length) paneYMinus--;
				
				if(texturePanes[paneXPlus][paneY].image.equals(image)) bucketFill(texturePanes[paneXPlus][paneY], paneXPlus, paneY, image, newImage);
				if(texturePanes[paneXMinus][paneY].image.equals(image)) bucketFill(texturePanes[paneXMinus][paneY], paneXMinus, paneY, image, newImage);
				if(texturePanes[paneX][paneYPlus].image.equals(image)) bucketFill(texturePanes[paneX][paneYPlus], paneX, paneYPlus, image, newImage);
				if(texturePanes[paneX][paneYMinus].image.equals(image)) bucketFill(texturePanes[paneX][paneYMinus], paneX, paneYMinus, image, newImage);
			}
		}
		
		private BufferedImage image;
		private int imageId = -1, rotation = 0, xShift = 0, yShift = 0;
		
		public TexturePane() {
			this(null);
		}
		
		public TexturePane(final BufferedImage image) {
			this.image = image;
			
			setLayout(null);
			setBackground(new Color(199, 199, 199));
			setBorder(new LineBorder(Color.LIGHT_GRAY));
			addMouseListener(tilePaneMouseListener);
		}
		
		@Override
		public void paintComponent(final Graphics g) {
			super.paintComponent(g);
			
			if(image != null) g.drawImage(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
		}
		
		public void setImage(final BufferedImage image, int xShift, int yShift) {
			this.image = image != null ? image.getSubimage(xShift, yShift, 32, 32) : null;
			this.xShift = xShift;
			this.yShift = yShift;
			
			repaint();
		}
		
		public void setImage(final BufferedImage image) {
			setImage(image, 0, 0);
		}
		
		public void setRotated(final int angle) {
			setImage(RPGWorldCreator.rotateImage(image, angle));
			
			rotation += angle;
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
		cursors.put(name, toolkit.createCustomCursor(RPGWorldCreator.getImage("/assets/worldcreator/cursors/" + name + ".png"), hotSpot, name));
	}
	
	@Override
	public JPanel getContentPane() {
		return (JPanel) super.getContentPane();
	}
}
