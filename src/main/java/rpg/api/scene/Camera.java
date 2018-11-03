package rpg.api.scene;

import java.awt.Dimension;
import java.awt.Toolkit;

import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * Camera stores the ingame coordinate of the top left corner and the width and
 * height of the view.
 */
public class Camera {
	public static UnmodifiableVec2D location = Vec2D.ORIGIN;
	public static Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
}
