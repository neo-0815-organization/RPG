package rpg.api.gfx;

/**
 * This interface represents every thing that can be drawn.
 *
 * @author Erik Diers
 */
public interface IDrawable {
	
	/**
	 * Method to draw any thing that can be drawn.
	 *
	 * @param g
	 *            the {@link DrawingGraphics} object to draw on
	 */
	void draw(final DrawingGraphics g);
	
	default void drawStack(final DrawingGraphics g) {
		g.push();
		
		draw(g);
		
		g.pop();
	}
}
