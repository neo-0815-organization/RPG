package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;

public abstract class Controller {
	
	public abstract void setDisplayName(String displayName);
	
	public abstract void setLocation(Location location);
	
	public abstract void setLookingDirection(Direction direction);
	
	public abstract void setVelocity(Vec2D vector);
	
	public abstract void setUniqueId(UUID uuid); 
}
