package rpg.api.entity;

import rpg.api.Direction;
import rpg.api.Location;

public abstract class Controller {
	
	public abstract void setLocation(Location location);
	
	public abstract void setLookingDirection(Direction direction);
}
