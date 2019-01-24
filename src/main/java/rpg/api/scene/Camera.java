package rpg.api.scene;

import java.awt.Dimension;
import java.awt.Toolkit;

import rpg.api.tile.Tile;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * Stores display information of the view.
 *
 * @author Erik Diers, Tim Ludwig
 */
public class Camera {
	/**
	 * The ingame coordinate of the top left corner of the view (in {@link Tile}s).
	 */
	public static UnmodifiableVec2D location = Vec2D.ORIGIN;
	
	/**
	 * The {@link Dimension} of the view (in pixels).
	 */
	public static Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * The factor to scale by after drawing.
	 */
	public static final double scale = 2D;
}
