package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rpg.RPG;
import rpg.api.scene.Camera;

public interface IImage extends IDrawable {
	public BufferedImage getImage();
	
	public default Rectangle getBounds() {
		return new Rectangle(0, 0, getImage().getWidth(), getImage().getHeight());
	}
	
	public default void draw(final Graphics2D g2d, final int x, final int y) {
		final int camX = Camera.loc.getX().getValuePixel(),
				camY = Camera.loc.getY().getValuePixel(),
				w = (int) getBounds().getWidth(),
				h = (int) getBounds().getHeight(),
				camXW = camX + RPG.SCREEN_WIDTH,
				camYH = camY + RPG.SCREEN_HEIGHT;
		
		if((x < camX && camX < w || x < camXW && camXW < w) && (x < camY && camY < h || x < camYH && camYH < h)) g2d.drawImage(getImage(), x, y, null);
	}
}
