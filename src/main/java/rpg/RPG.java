package rpg;

import rpg.api.eventhandling.EventHandler;
import rpg.api.eventhandling.EventListener;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.Event;

public class RPG {
	public static void main(final String[] args) {
		final EventHandler h = new EventHandler();
		
		h.registerEventListener(new EventListener() {
			@Override
			public EventType getEventType() {
				return EventType.TEST_EVENT;
			}
			
			@Override
			public void onEvent(final Event event) {
				System.out.println("test_event");
			}
		});
		
		h.registerEventListener(new EventListener() {
			@Override
			public EventType getEventType() {
				return EventType.GENERAL_EVENT;
			}
			
			@Override
			public void onEvent(final Event event) {
				System.out.println("general_event");
			}
		});
		
		h.handle(new Event() {
			@Override
			public EventType getEventType() {
				return EventType.TEST_EVENT;
			}
		});
		
		h.handle(new Event() {
			@Override
			public EventType getEventType() {
				return EventType.GENERAL_EVENT;
			}
		});
	}
}
