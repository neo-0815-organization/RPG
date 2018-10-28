package rpg.api.scene;

import java.awt.Dimension;
import java.awt.Toolkit;

import rpg.api.Vec2D;

/**
 * The class Camera stores the ingame coordinate of the top left corner and the
 * width and
 * height of the view.
 *
 * @author Erik Diers, Tim Ludwig
 */
public class Camera {
	public static Vec2D		location	= Vec2D.ORIGIN;
	public static Dimension	frameSize	= Toolkit.getDefaultToolkit().getScreenSize();
}
