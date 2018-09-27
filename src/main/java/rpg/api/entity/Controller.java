package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;

public interface Controller {

	public void setDisplayName(String displayName);
	
	public void setLocation(Location location);
	
	public void setLookingDirection(Direction direction);
	
	public void setVelocity(Vec2D vector);
	
	public void setUniqueId(UUID uuid);
}
