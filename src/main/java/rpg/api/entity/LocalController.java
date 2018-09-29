package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;

public class LocalController implements Controller {
	private Entity entity;
	
	@Override
	public void setDisplayName(String displayName) {
		entity.displayName = displayName;
	}

	@Override
	public void setLocation(Location location) {
		entity.location = location;
	}

	@Override
	public void setLookingDirection(Direction direction) {
		entity.lookingDirection = direction;
	}

	@Override
	public void setVelocity(Vec2D velocity) {
		entity.velocity = velocity;
	}

	@Override
	public void setUniqueId(UUID uuid) {
		entity.uuid = uuid;
	}
}