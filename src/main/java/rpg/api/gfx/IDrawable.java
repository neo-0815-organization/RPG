package rpg.api.gfx;

import java.awt.Graphics2D;

/**
 * This interface represents every thing that can be drawn.
 *
 * @author Erik Diers
 */
public interface IDrawable {
	
	/**
	 * Method to draw any thing that can be drawn.
	 *
	 * @param g2d
	 *     the {@link Graphics2D} object to draw on
	 */
	void draw(Graphics2D g2d);
}
