package rpg.api.gfx;

import java.awt.Graphics2D;

public interface IDrawable {
	/**
	 * Method to draw any sub-class of {@link IDrawable}
	 * 
	 * @param g2d
	 *        {@link Graphics2D} object to draw on
	 */
	void draw(Graphics2D g2d);
}
