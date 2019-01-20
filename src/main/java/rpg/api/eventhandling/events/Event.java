package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;

/**
 * The interface Event.
 *
 * @author Tim Ludiwg
 */
public interface Event {
	
	/**
	 * Gets the {@link EventType} of this {@link Event}.
	 *
	 * @return the {@link EventType} of this {@link Event}
	 */
	default EventType getEventType() {
		return EventType.GENERAL_EVENT;
	}
}
