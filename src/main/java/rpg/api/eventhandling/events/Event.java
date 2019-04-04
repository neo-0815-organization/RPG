package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;

/**
 * The interface Event.
 *
 * @author Tim Ludiwg
 */
public class Event {
	public EventType type;
	
	public Event(EventType type) {
		this.type = type;
	}
	
	/**
	 * Gets the {@link EventType} of this {@link Event}.
	 *
	 * @return the {@link EventType} of this {@link Event}
	 */
	public final EventType getEventType() {
		return type;
	}
}
