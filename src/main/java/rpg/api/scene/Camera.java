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
			location.setX(new DistanceValue(entityToFollow.getLocation().getX().getValuePixel() + (entityToFollow.getWidth() - Statics.frameSize.width) / SCALED_2));
			location.setY(new DistanceValue(entityToFollow.getLocation().getY().getValuePixel() + (entityToFollow.getHeight() - Statics.frameSize.height) / SCALED_2));
			
			if(location.getValueX() < 0) location.setValueX(0);
			else if(location.getX().getValuePixel() > RPG.gameField.save.background.getWidth() - Statics.gameSize.width) location.setX(new DistanceValue(RPG.gameField.save.background.getWidth() - Statics.gameSize.width));
			if(location.getValueY() < 0) location.setValueY(0);
			else if(location.getY().getValuePixel() > RPG.gameField.save.background.getHeight() - Statics.gameSize.height) location.setY(new DistanceValue(RPG.gameField.save.background.getHeight() - Statics.gameSize.height));
		}
	}
	
	public static void setFocusEntity(final Entity e) {
		entityToFollow = e;
	}
}
