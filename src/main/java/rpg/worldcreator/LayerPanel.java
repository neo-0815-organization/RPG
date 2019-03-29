package rpg.worldcreator;

import static rpg.worldcreator.Data.tileSize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import rpg.api.gfx.ImageUtility;

public class LayerPanel extends JPanel {
	private static final long serialVersionUID = -2665204565190419104L;
	
	private final TwoValueMap<String, Integer, BufferedImage> pictures;
	private final int layer;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Dimension panelSize = new Dimension(0, 0);
	
	public LayerPanel(final TwoValueMap<String, Integer, BufferedImage> pictures, final int layer, final WorldCreatorFrame frameInstance) {
		this.pictures = pictures;
		this.layer = layer;
		
		setLayout(null);
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		if(RPGWorldCreator.isDarkmode()) setBackground(new Color(25, 29, 31));
		
		initComponents(frameInstance);
	}
	
	private void initComponents(final WorldCreatorFrame frameInstance) {
		pictures.forEach((name, id, image) -> {
			final int xNumber = image.getWidth() / tileSize, yNumber = image.getHeight() / tileSize, padding = 3;
			final double scaledSize = (134 - padding * 2) / xNumber;
			final JPanel panel = new JPanel();
			
			panel.setBounds(padding, (int) (id * yNumber * scaledSize + padding * (id + 1)), (int) (xNumber * scaledSize), (int) (yNumber * scaledSize));
			panel.setLayout(new GridLayout(yNumber, xNumber));
			
			if(panelSize.width < (int) (xNumber * scaledSize)) panelSize.width = (int) (xNumber * scaledSize) + padding;
			panelSize.height += (int) (yNumber * scaledSize) + padding;
			
			for(int x = 0; x < xNumber; x++)
				for(int y = 0; y < yNumber; y++) {
					final JToggleButton button = new JToggleButton(new ImageIcon(ImageUtility.scale(image.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize), (int) scaledSize, (int) scaledSize)));
					button.addActionListener(frameInstance.prefixActionListener);
					button.setActionCommand("texture:" + name + ":" + x + "|" + y + "|" + layer);
					button.setFocusPainted(true);
					
					buttonGroup.add(button);
					panel.add(button);
				}
			
			add(panel);
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		return panelSize;
	}
}
