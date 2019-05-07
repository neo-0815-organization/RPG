package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;
import rpg.api.entity.Entity;

public class CollisionEvent extends Event{
	private Entity trigger, target;

	public CollisionEvent(Entity trigger, Entity target) {
		super(EventType.COLLISION_EVENT);
		this.trigger = trigger;
		this.target = target;
	}
	
	public Entity getTarget() {
		return target;
	}
	
	public Entity getTrigger() {
		return trigger;
	}
}
