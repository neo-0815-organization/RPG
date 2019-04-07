package rpg.api.eventhandling;

import rpg.api.eventhandling.events.Event;

/**
 * The listener interface for receiving {@link Event}s.
 *
 * @see Event
 * @author Tim Ludwig
 */
@FunctionalInterface
public interface EventListener {
	
	/**
	 * Gets the {@link EventType} this {@link EventListener} is supposed to
	 * receive.
	 *
	 * @return the {@link EventType} this {@link EventListener} receives
	 */
	default EventType getEventType() {
		return EventType.GENERAL_EVENT;
	}
	
	/**
	 * The method to execute on receiving an {@link Event}.
	 *
	 * @param event
	 *            the {@link Event} received
	 */
	void onEvent(final Event event);
}
