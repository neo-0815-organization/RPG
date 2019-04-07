package rpg.api.gfx;

import java.awt.image.BufferedImage;

import rpg.Statics;
import rpg.api.scene.Camera;
import rpg.api.vector.Vec2D;

/**
 * This interface represents every IDrawable that has to draw a
 * {@link BufferedImage}.
 *
 * @author Tim Ludwig
 */
public interface IImage extends IDrawable {
	BufferedImage getImage();
	
	/**
	 * Gets the width of the {@link BufferedImage} corresponding to this {@link IImage}. 
	 *
	 * @return the width of the {@link BufferedImage}
	 */
	default int getWidth() {
		return getImage().getWidth();
	}
	
	/**
	 * Gets the height of the {@link BufferedImage} corresponding to this {@link IImage}.
	 *
	 * @return the height of the {@link BufferedImage}
	 */
	default int getHeight() {
		return getImage().getHeight();
	}
	
	/**
	 * Draws the {@link BufferedImage} corresponding to this {@link IImage}.
	 *
	 * @param g
	 *            the {@link Graphics2D} object to draw on
	 * @param location
	 *            the location {@link Vec2D} to draw at
	 */
	default void draw(final DrawingGraphics g, final Vec2D<?> location) {
		//@formatter:off
		final int   x = location.getX().getValuePixel(),
				    y = location.getY().getValuePixel(),
				 camX = Camera.location.getX().getValuePixel(),
				 camY = Camera.location.getY().getValuePixel(),
				    w = getWidth(),
				    h = getHeight(),
				camXW = camX + Statics.frameSize.width,
				camYH = camY + Statics.frameSize.height;
		//@formatter:on
		
		if((x < camX + w || x < camXW) && (y < camY + h || y < camYH)) g.drawImage(getImage(), x - Camera.location.getX().getValuePixel(), y - Camera.location.getY().getValuePixel(), null);
	}
}
