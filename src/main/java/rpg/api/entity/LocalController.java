package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Vec2D;

public class LocalController implements Controller {
	private Entity entity;
	
	@Override
	public void setDisplayName(final String displayName) {
		entity.displayName = displayName;
	}
	
	@Override
	public void setLocation(final Vec2D location) {
		entity.location = location;
	}
	
	@Override
	public void setLookingDirection(final Direction direction) {
		entity.lookingDirection = direction;
	}
	
	@Override
	public void setVelocity(final Vec2D velocity) {
		entity.velocity = velocity;
	}
	
	@Override
	public void setUniqueId(final UUID uuid) {
		entity.uuid = uuid;
	}
}