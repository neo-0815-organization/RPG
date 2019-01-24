package rpg.api.scene;

import java.awt.Dimension;
import java.awt.Toolkit;

import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The class Camera stores the ingame coordinate of the top left corner and the
 * width and height of the view.
 *
 * @author Erik Diers, Tim Ludwig
 */
public class Camera {
	public static UnmodifiableVec2D location = Vec2D.ORIGIN;
	public static final Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double scale = 2D;
}
