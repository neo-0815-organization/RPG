package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import rpg.api.gfx.ImageUtility;
import rpg.worldcreator.Data;
import rpg.worldcreator.WorldCreatorFrame;
import rpg.worldcreator.WorldCreatorFrame.SpritePane;
import rpg.worldcreator.WorldCreatorHitbox;

public class EditHitboxDialog extends JDialog {
	private static final long serialVersionUID = 4801731920330519103L;
	
	private final SpritePane pane;
	private final WorldCreatorHitbox fluidBox, tileBox;
	
	private final JTabbedPane layerPanels;
	private final JPanel fluidsPanel, tilesPanel;
	
	public EditHitboxDialog(final WorldCreatorFrame frame, final SpritePane pane) {
		super(frame, "Hitbox Tool", true);
		
		this.pane = pane;
		
		fluidBox = pane.getHitboxCopy(0);
		tileBox = pane.getHitboxCopy(2);
		
		setSize(700, 500);
		setLocationRelativeTo(frame);
		setLayout(new BorderLayout());
		
		layerPanels = new JTabbedPane(JTabbedPane.LEFT);
		
		fluidsPanel = new LayerPanel(fluidBox);
		layerPanels.addTab("Fluid", fluidsPanel);
		
		tilesPanel = new LayerPanel(tileBox);
		layerPanels.addTab("Tile", tilesPanel);
		
		layerPanels.setSelectedIndex(1);
		
		add(layerPanels, BorderLayout.CENTER);
	}
	
	private class LayerPanel extends JPanel {
		private static final long serialVersionUID = -7316328327119894614L;
		
		private final WorldCreatorHitbox hitbox;
		
		private final JCheckBox editMode;
		private final JPanel panelContainer, previewHolder;
		private final PointInput width, height;
		
		public LayerPanel(final WorldCreatorHitbox hitbox) {
			this.hitbox = hitbox;
			
			setLayout(new BorderLayout());
			
			editMode = new JCheckBox("Should have hitbox", true);
			editMode.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(final ItemEvent e) {
					hitbox.setType(!editMode.isSelected());
					
					width.setEnabled(editMode.isSelected());
					height.setEnabled(editMode.isSelected());
				}
			});
			add(editMode, BorderLayout.NORTH);
			
			panelContainer = new JPanel();
			panelContainer.setLayout(null);
			
			width = new PointInput("Width", false, 350, 25);
			width.setLocation(100, 100);
			width.x.setValue(this.hitbox.getDimension().getWidth());
			panelContainer.add(width);
			
			height = new PointInput("Height", false, 350, 25);
			height.setLocation(100, 150);
			height.x.setValue(this.hitbox.getDimension().getHeight());
			panelContainer.add(height);
			
			add(panelContainer, BorderLayout.CENTER);
			
			final JPanel previewPanel = new JPanel() {
				private static final long serialVersionUID = 3649405991511062410L;
				
				@Override
				protected void paintComponent(final Graphics g) {
					super.paintComponent(g);
					
					int newSize = getHeight();
					if(newSize > getWidth()) newSize = getWidth();
					
					newSize -= 10;
					
					g.translate(5, 5);
					g.drawImage(ImageUtility.scale(pane.getImages(), newSize, newSize), 0, 0, null);
					
					final Graphics2D g2d = (Graphics2D) g;
					final double scale = newSize / (double) Data.tileSize;
					
					g2d.scale(scale, scale);
					hitbox.setScale(1d);
					hitbox.draw(g);
					
					g.dispose();
				}
			};
			
			previewHolder = new JPanel();
			previewHolder.setLayout(new BorderLayout());
			previewHolder.setBorder(new LineBorder(Color.LIGHT_GRAY));
			previewHolder.setPreferredSize(new Dimension(150, 0));
			previewHolder.add(previewPanel);
			
			final JButton refreshButton = new JButton("Refresh");
			refreshButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(final ActionEvent e) {
					updateHitbox();
				}
			});
			previewHolder.add(refreshButton, BorderLayout.SOUTH);
			add(previewHolder, BorderLayout.WEST);
			
			editMode.setSelected(!this.hitbox.isNull());
		}
		
		public void updateHitbox() {
			hitbox.getDimension().setSize(convertStringToDouble(width.x.getText()), convertStringToDouble(height.x.getText()));
			
			previewHolder.revalidate();
			previewHolder.repaint();
		}
		
		private double convertStringToDouble(final String s) {
			if(s.isEmpty()) return 0;
			
			return Double.parseDouble(s);
		}
	}
	
	private class PointInput extends JPanel {
		private static final long serialVersionUID = 7343936080384113644L;
		
		private JFormattedTextField x, y;
		
		public PointInput(final String title, final boolean twoTuple, final int width, final int height) {
			setSize(width, height);
			setLayout(null);
			
			final int textFieldWidth = (width - 145) / 2;
			final DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
			final DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
			
			symbols.setDecimalSeparator('.');
			format.setDecimalFormatSymbols(symbols);
			
			final JLabel titleLabel = new JLabel(title);
			titleLabel.setBounds(0, 0, 75, height);
			add(titleLabel);
			
			if(twoTuple) {
				final JLabel openingParenthesis = new JLabel("(");
				openingParenthesis.setBounds(80, 0, 10, height);
				add(openingParenthesis);
				
				x = new JFormattedTextField(format);
				x.setBounds(95, 0, textFieldWidth, height);
				add(x);
				
				final JLabel semicolon = new JLabel(";");
				semicolon.setBounds(110 + textFieldWidth, 0, 10, height);
				add(semicolon);
				
				y = new JFormattedTextField(format);
				y.setBounds(width - 15 - textFieldWidth, 0, textFieldWidth, height);
				add(y);
				
				final JLabel closingParenthesis = new JLabel(")");
				closingParenthesis.setBounds(width - 10, 0, 10, height);
				add(closingParenthesis);
			}else {
				x = new JFormattedTextField(format);
				x.setBounds(width / 2 - textFieldWidth / 2 + 40, 0, textFieldWidth, height);
				add(x);
			}
		}
		
		@Override
		public void setEnabled(final boolean enabled) {
			super.setEnabled(enabled);
			
			x.setEnabled(enabled);
			if(y != null) y.setEnabled(enabled);
		}
	}
	
	public WorldCreatorHitbox getHitbox(final int layer) {
		switch(layer) {
			case 0:
				return fluidBox;
			case 2:
				return tileBox;
		}
		
		return WorldCreatorHitbox.nullBox;
	}
}
