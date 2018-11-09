package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.vector.Vec2D;

public class EntityCreator {
	
	public static Entity create(final String displayName) {
		return new Entity(displayName) {};
	}
	
	public static Entity create(final String displayName, final Vec2D<?> location, final Vec2D<?> velocity, final Direction lookingDirection, final String imageName, final UUID uuid) {
		final Entity e = create(displayName);
		
		e.location = location.toModifiable();
		e.velocity = velocity.toModifiable();
		e.lookingDirection = lookingDirection;
		e.imageName = imageName;
		e.uuid = uuid;
		
		return e;
	}
}
