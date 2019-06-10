package rpg.api.tile;

import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventType;
import rpg.api.vector.Vec2D;

public abstract class Fluid extends Tile {
	public final Vec2D<?> acceleration;
	
	public Fluid(final Vec2D<?> acceleration) {
		this.acceleration = acceleration;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		if(eventType == EventType.COLLISION_EVENT) {
			final Entity e = (Entity) objects[1];
			
			e.getVelocity().add(acceleration);
		}
	}
}
