package rpg.api.eventhandling;

import java.util.HashMap;
import java.util.LinkedList;

import rpg.api.eventhandling.events.Event;

/**
 * The class EventHandler, used for handling {@link Event}s of different
 * {@link EventType}s.
 *
 * @author Tim Ludwig
 */
public class EventHandler {
	private static HashMap<EventType, LinkedList<EventListener>> listener = new HashMap<>();
	private static HashMap<EventType, Boolean> eventTypeRegistered = initEventTypeRegistered();
	
	/**
	 * Initiates the known {@link EventType}s as unregistered.
	 *
	 * @return the {@link HashMap} containing the registration state of the
	 *         {@link EnventType}s.
	 */
	private static HashMap<EventType, Boolean> initEventTypeRegistered() {
		final HashMap<EventType, Boolean> eventTypeRegistered = new HashMap<>();
		
		for(final EventType et : EventType.values())
			eventTypeRegistered.put(et, false);
		
		return eventTypeRegistered;
	}
	
	/**
	 * Checks if the {@link EventType} 'eventType' is registered.
	 *
	 * @param eventType
	 *            the {@link EventType} to check
	 * @return {@code true} if the {@link EventType} is registered.
	 */
	private static boolean isEventTypeRegistered(final EventType eventType) {
		return eventTypeRegistered.get(eventType);
	}
	
	/**
	 * Gets the {@link EventListener}s for the {@link EventType} 'eventType'.
	 *
	 * @param eventType
	 *            the type of the {@link Event} to search for
	 *            {@link EventListener}s
	 * @return the {@link EventListener}s for the {@link EventType} 'eventType'
	 */
	private static LinkedList<EventListener> getListenersForEventType(final EventType eventType) {
		return listener.get(eventType);
	}
	
	/**
	 * Handles the {@link Event} 'event'.
	 *
	 * @param event
	 *            the {@link Event} to handle
	 */
	public static void handle(final Event event) {
		final EventType t = event.getEventType();
		
		if(!isEventTypeRegistered(t)) return;
		
		new Thread((Runnable) () -> {
			for(final EventListener l : getListenersForEventType(t))
				new Thread((Runnable) () -> l.onEvent(event), "Listener " + l + " handling " + t).start();
		}, "Handling " + event).start();
	}
	
	/**
	 * Registers the {@link EventListener} 'eventListener'.
	 *
	 * @param eventListener
	 *            the {@link EventListener} to register
	 */
	public static void registerEventListener(final EventListener eventListener) {
		final EventType eT = eventListener.getEventType();
		
		if(!listener.containsKey(eT)) {
			listener.put(eT, new LinkedList<>());
			eventTypeRegistered.put(eT, true);
		}
		
		listener.get(eT).add(eventListener);
	}
}
