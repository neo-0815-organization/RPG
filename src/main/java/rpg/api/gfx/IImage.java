package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import rpg.RPG;
import rpg.api.Vec2D;
import rpg.api.scene.Camera;

public interface IImage extends IDrawable {
	public BufferedImage getImage();
	
	/**
	 * Gets the width of the {@link BufferedImage} this interface represents
	 * 
	 * @return the width of the {@link BufferedImage}
	 */
	public default int getWidth() {
		return getImage().getWidth();
	}
	
	/**
	 * Gets the height of the {@link BufferedImage} this interface represents
	 * 
	 * @return the height of the {@link BufferedImage}
	 */
	public default int getHeight() {
		return getImage().getHeight();
	}
	
	/**
	 * Draws the {@link BufferedImage} this interface represents
	 * 
	 * @param g2d
	 *        the {@link Graphics2D} object to draw on
	 * @param location
	 *        the location to draw at
	 */
	public default void draw(final Graphics2D g2d, final Vec2D location) {
		final int x = location.getX().getValuePixel(),
				y = location.getY().getValuePixel(),
				camX = Camera.location.getX().getValuePixel(),
				camY = Camera.location.getY().getValuePixel(),
				w = getWidth(),
				h = getHeight(),
				camXW = camX + RPG.SCREEN_WIDTH,
				camYH = camY + RPG.SCREEN_HEIGHT;
		
		if((x < camX && camX < w || x < camXW && camXW < w) && (x < camY && camY < h || x < camYH && camYH < h)) g2d.drawImage(getImage(), x, y, null);
	}
}
