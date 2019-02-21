package rpg.api.scene;

import java.awt.Dimension;
import java.awt.Toolkit;

import rpg.api.entity.Entity;
import rpg.api.units.DistanceValue;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The class Camera stores the ingame coordinate of the top left corner and the
 * width and height of the view.
 *
 * @author Erik Diers, Tim Ludwig
 */
public class Camera {
	public static ModifiableVec2D location = Vec2D.ORIGIN.toModifiable();
	public static final Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double scale = 2D;
	private static Entity entityToFollow;
	
	public static void update() {
		if (entityToFollow != null) {
			location.setX(new DistanceValue(entityToFollow.getLocation().getX().getValuePixel() - frameSize.width / 4 + entityToFollow.getWidth() / 2));
			location.setY(new DistanceValue(entityToFollow.getLocation().getY().getValuePixel() - frameSize.height / 4  + entityToFollow.getHeight() / 2));
		}
	}
	
	public static void setFocusEntity(Entity e) {
		entityToFollow = e;
	}
}
