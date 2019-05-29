package rpg.api.gfx;

import java.awt.Graphics2D;

import rpg.Statics;
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
	 * @param g
	 *            the {@link Graphics2D} object to draw on
	 * @param location
	 *            the location to draw at
	 */
	default void draw(final DrawingGraphics g, final Vec2D<?> location) {
		if(getSprite() == null) return;
		
		//@formatter:off
		final int 	x = location.getX().getValuePixel(),
					y = location.getY().getValuePixel(),
				 camX = Camera.location.getX().getValuePixel(),
				 camY = Camera.location.getY().getValuePixel(),
				   xw = x + getWidth(),
				   yh = y + getHeight(),
				camXW = camX + Statics.frameSize.width,
				camYH = camY + Statics.frameSize.height;
		//@formatter:on
		
		if((x > camX && x < camXW || xw > camX && xw < camXW) && (y > camY && y < camYH || yh > camY && yh < camYH)) g.drawImage(getSprite().getCurrentAnimationFrame(), x - Camera.location.getX().getValuePixel(), y - Camera.location.getY().getValuePixel(), null);
	}
}
