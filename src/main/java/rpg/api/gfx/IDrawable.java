package rpg.api.gfx;

import java.awt.Graphics2D;

public interface IDrawable {
	
	/**
	 * Is responsible for drawing things.
	 * 
	 * @param g2d
	 *            GraphicsObject which is going to be drawed.
	 */
	public void draw(Graphics2D g2d);
}
