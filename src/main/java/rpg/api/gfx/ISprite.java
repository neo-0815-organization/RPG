package rpg.api.gfx;

import java.awt.Graphics2D;

import rpg.api.scene.Camera;
import rpg.api.vector.Vec2D;

public interface ISprite extends IDrawable {
	Sprite getSprite();
	
	/**
	 * Gets the width of the {@link Sprite} which this interface is
	 * representing.
	 *
	 * @return the width of the {@link Sprite}
	 * @see Sprite#getWidth()
	 */
	default int getWidth() {
		return getSprite().getWidth();
	}
	
	/**
	 * Gets the height of the {@link Sprite} which this interface is
	 * representing.
	 *
	 * @return the height of the {@link Sprite}
	 * @see Sprite#getHeight()
	 */
	default int getHeight() {
		return getSprite().getHeight();
	}
	
	/**
	 * Draws the {@link Sprite} which this interface is representing.
	 *
	 * @param g2d
	 *            the {@link Graphics2D} object to draw on
	 * @param location
	 *            the location to draw at
	 */
	default void draw(final Graphics2D g2d, final Vec2D<?> location) {
		if(getSprite() == null) return;
		
		//@formatter:off
		final int 	x = location.getX().getValuePixel(),
					y = location.getY().getValuePixel(),
				 camX = Camera.location.getX().getValuePixel(),
				 camY = Camera.location.getY().getValuePixel(),
				   xw = x + getWidth(),
				   yh = y + getHeight(),
				camXW = camX + (int) Camera.frameSize.getWidth(),
				camYH = camY + (int) Camera.frameSize.getHeight();
		//@formatter:on
		
		if((x > camX && x < camXW || xw > camX && xw < camXW) && (y > camY && y < camYH || yh > camY && yh < camYH)) g2d.drawImage(getSprite().getCurrentAnimationFrame(), x, y, null);
	}
}
