package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;

public class LocalController extends Controller {
	private Player player;
	
	@Override
	public void setDisplayName(String displayName) {
		player.displayName = displayName;
	}

	@Override
	public void setLocation(Location location) {
		player.location = location;
	}

	@Override
	public void setLookingDirection(Direction direction) {
		player.lookingDirection = direction;
	}

	@Override
	public void setVelocity(Vec2D velocity) {
		player.velocity = velocity;
	}

	@Override
	public void setUniqueId(UUID uuid) {
		player.uuid = uuid;
	}
}
