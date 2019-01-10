package rpg.api.gfx.framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class RPGButton extends JButton{
	private static final long serialVersionUID = 6887580054889086469L;
	BufferedImage image;
	
	public RPGButton(BufferedImage img) {
		image = img;
	}
	
	
	public void setImage(BufferedImage image, boolean enableBounderyCorrection) {
		this.image = image;
		if(enableBounderyCorrection) {
			setBounds(getBounds().x, getBounds().y, image.getWidth(), image.getHeight());
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D)g).drawImage(image, getBounds().x, getBounds().y, null);
//		super.paintComponent(g);
	}
	
}
