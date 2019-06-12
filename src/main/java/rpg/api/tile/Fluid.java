package rpg.api.tile;

import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventType;
import rpg.api.vector.UnmodifiableVec2D;

public abstract class Fluid extends Tile {
	public UnmodifiableVec2D acceleration;
	
	public Fluid(final UnmodifiableVec2D acceleration) {
		this.acceleration = acceleration;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		if(eventType == EventType.COLLISION_EVENT) {
			final Entity e = (Entity) objects[1];
			
			e.addAccVel(acceleration);
		}
	}
}
