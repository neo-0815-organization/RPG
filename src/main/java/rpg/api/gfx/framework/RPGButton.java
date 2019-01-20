package rpg.api.gfx.framework;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import rpg.RPG;
import rpg.api.filereading.ResourceGetter;
import rpg.api.gfx.GameFrame;
import rpg.api.gfx.ImageUtility;
import rpg.api.localization.StringLocalizer;

/**
 * This 
 *
 */
public class RPGButton extends JButton{
	private static final long serialVersionUID = 6887580054889086469L;
	private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 24);
	private static final BufferedImage BUTTON_TEMPLATE = ResourceGetter.getImage("/assets/textures/menu/button.png");
	
	private final BufferedImage image;
	
	public RPGButton(String title) {
		this(title, BUTTON_TEMPLATE);
	}
	
	public RPGButton(String title, BufferedImage image) {
		this(title, DEFAULT_FONT, image);
	}
	
	public RPGButton(String title, Font font, BufferedImage image) {
		super(StringLocalizer.localize(title));
		
		this.image = image;
		
		setFont(font);
		setVerticalTextPosition(JButton.CENTER);
		setHorizontalTextPosition(JButton.CENTER);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		setIcon(new ImageIcon(ImageUtility.scale(image, width, height)));
	}
	
	
}
