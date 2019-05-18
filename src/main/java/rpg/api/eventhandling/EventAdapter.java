package rpg.api.eventhandling;

import rpg.api.eventhandling.events.CollisionEvent;
import rpg.api.eventhandling.events.Event;

/**
 * The listener interface adapter for receiving {@link Event}s on specific
 * methods.
 *
 * @see EventListener
 * @see Event
 * @author Neo Hornberger
 */
public class EventAdapter implements EventListener {
	
	public void onCollision(final CollisionEvent e) {}
	
	@Override
	public void onEvent(final Event event) {
		if(event instanceof CollisionEvent) onCollision((CollisionEvent) event);
	}
	
	@Override
	public EventType getEventType() {
		return EventType.ALL_EVENTS;
	}
}
