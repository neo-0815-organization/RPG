package rpg.api.eventhandling;

import rpg.api.eventhandling.events.Event;

public abstract class EventListener {
	EventType eventType;
	
	public EventType getEventType() {
		return eventType;
	}
	
	public abstract void onEvent(final Event event);
}
