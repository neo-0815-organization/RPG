package rpg.api.scene;

import rpg.Statics;
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
	
	private static Entity entityToFollow;
	
	public static void update() {
		if(entityToFollow != null) {
			location.setX(new DistanceValue(entityToFollow.getLocation().getX().getValuePixel() + entityToFollow.getWidth() / 2 - Statics.frameSize.width / Statics.scale(2)));
			location.setY(new DistanceValue(entityToFollow.getLocation().getY().getValuePixel() + entityToFollow.getHeight() / 2 - Statics.frameSize.height / Statics.scale(2)));
		}
	}
	
	public static void setFocusEntity(final Entity e) {
		entityToFollow = e;
	}
}
