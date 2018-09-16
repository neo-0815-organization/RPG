package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;

public abstract class Event {
	private final EventType eventType = EventType.EVENT;
	
	public EventType getEventType() {
		return eventType;
	}
}
