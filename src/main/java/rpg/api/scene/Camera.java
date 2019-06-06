package rpg.api.scene;

import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.Entity;
import rpg.api.units.DistanceValue;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * Stores display information of the view.
 *
 * @author Erik Diers, Tim Ludwig
 */
public class Camera {
	public static ModifiableVec2D location = Vec2D.ORIGIN.toModifiable();
	
	private static Entity entityToFollow;
	private static int SCALED_2 = Statics.scale(2);
	
	public static void update() {
		if(entityToFollow != null) {
			if(entityToFollow.getLocation().getX().getValuePixel() <= Statics.frameSize.width / SCALED_2) {
				location.setX(new DistanceValue(0));
			
			} else if(entityToFollow.getLocation().getX().getValuePixel() > RPG.gameField.save.background.getWidth() - Statics.frameSize.width / SCALED_2) {
				location.setX(new DistanceValue(Statics.scale(RPG.gameField.save.background.getWidth()) - Statics.frameSize.width));
				
			} else {
				location.setX(new DistanceValue(entityToFollow.getLocation().getX().getValuePixel() + (entityToFollow.getWidth() - Statics.frameSize.width) / SCALED_2));
			}
			if(entityToFollow.getLocation().getY().getValuePixel() <= Statics.frameSize.height / SCALED_2) {
				location.setY(new DistanceValue(0));
				
			} else if(entityToFollow.getLocation().getY().getValuePixel() > RPG.gameField.save.background.getHeight() - Statics.frameSize.height / SCALED_2) {
				location.setY(new DistanceValue(RPG.gameField.save.background.getHeight() - Statics.frameSize.height));
				
			} else {
				location.setY(new DistanceValue(entityToFollow.getLocation().getY().getValuePixel() + (entityToFollow.getHeight() - Statics.frameSize.height) / SCALED_2));
			}
		}
	}
	
	public static void setFocusEntity(final Entity e) {
		entityToFollow = e;
	}
}
