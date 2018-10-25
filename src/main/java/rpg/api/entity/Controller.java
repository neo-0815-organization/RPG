package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Vec2D;

public interface Controller {
	
	public void setDisplayName(String displayName);
	
	public void setLocation(Vec2D location);
	
	public void setLookingDirection(Direction direction);
	
	public void setVelocity(Vec2D velocity);
	
	public void setUniqueId(UUID uuid);
}
