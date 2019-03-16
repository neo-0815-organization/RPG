package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;

/**
 * The interface Event.
 *
 * @author Tim Ludiwg
 */
public class Event {
	public Object[] eventData;
	public EventType type;
	
	public Event(EventType type, Object... eventData) {
		this.eventData = eventData;
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
