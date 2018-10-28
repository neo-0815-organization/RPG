package rpg.api.gfx;

import java.awt.Graphics2D;

import rpg.api.Vec2D;
import rpg.api.scene.Camera;

public interface ISprite extends IDrawable {
	Sprite getSprite();
	
	/**
	 * Gets the width of the {@link Sprite} which this interface is representing.
	 *
	 * @return the width of the {@link Sprite}
	 * @see Sprite#getWidth()
	 */
	default int getWidth() {
		return getSprite().getWidth();
	}
	
	/**
	 * Gets the height of the {@link Sprite} which this interface is representing.
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
	 *     the {@link Graphics2D} object to draw on
	 * @param location
	 *     the location to draw at
	 */
	default void draw(final Graphics2D g2d, final Vec2D location) {
		final int x = location.getX().getValuePixel(),
				y = location.getY().getValuePixel(),
				camX = Camera.location.getX().getValuePixel(),
				camY = Camera.location.getY().getValuePixel(),
				w = getWidth(),
				h = getHeight(),
				camXW = camX + (int) Camera.frameSize.getWidth(),
				camYH = camY + (int) Camera.frameSize.getHeight();
		
		if((x < camX && camX < w || x < camXW && camXW < w) && (x < camY && camY < h || x < camYH && camYH < h)) g2d.drawImage(getSprite().getCurrentAnimationFrame(), x, y, null);
	}
}
