package rpg.api.eventhandling;

import rpg.api.eventhandling.events.Event;

public class EventListener {
	EventType eventType;
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void onEvent(final Event event) {
		
	}
}
