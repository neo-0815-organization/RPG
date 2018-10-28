package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import rpg.api.Vec2D;
import rpg.api.scene.Camera;

/**
 * This interface represents every IDrawable that has to draw a
 * {@link BufferedImage}.
 *
 * @author Tim Ludwig
 */
public interface IImage extends IDrawable {
	BufferedImage getImage();
	
	/**
	 * Gets the width of the {@link BufferedImage} which this interface is
	 * representing.
	 *
	 * @return the width of the {@link BufferedImage}
	 */
	default int getWidth() {
		return getImage().getWidth();
	}
	
	/**
	 * Gets the height of the {@link BufferedImage} which this interface is
	 * representing.
	 *
	 * @return the height of the {@link BufferedImage}
	 */
	default int getHeight() {
		return getImage().getHeight();
	}
	
	/**
	 * Draws the {@link BufferedImage} which this interface is representing.
	 *
	 * @param g2d
	 *     the {@link Graphics2D} object to draw on
	 * @param location
	 *     the location {@link Vec2D} to draw at
	 */
	default void draw(final Graphics2D g2d, final Vec2D location) {
		final int x = location.getX().getValuePixel(),
				y = location.getY().getValuePixel(),
				camX = Camera.location.getX().getValuePixel(),
				camY = Camera.location.getY().getValuePixel(),
				w = getWidth(),
				h = getHeight(),
				camXW = (int) (camX + Camera.frameSize.getWidth()),
				camYH = (int) (camY + Camera.frameSize.getHeight());
		
		if((x < camX && camX < w || x < camXW && camXW < w) && (x < camY && camY < h || x < camYH && camYH < h)) g2d.drawImage(getImage(), x, y, null);
	}
}
