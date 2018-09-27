package rpg.api.eventhandling;

import rpg.api.eventhandling.events.Event;

public abstract class EventListener {
	
	public abstract EventType getEventType();
	
	public abstract void onEvent(final Event event);
}
