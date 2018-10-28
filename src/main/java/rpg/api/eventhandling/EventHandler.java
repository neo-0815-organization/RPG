package rpg.api.eventhandling;

import java.util.ArrayList;
import java.util.HashMap;

import rpg.api.eventhandling.events.Event;

/**
 * The Class EventHandler.
 *
 * @author Tim Ludwig
 */
public class EventHandler {
	private static HashMap<EventType, ArrayList<EventListener>>	listener			= new HashMap<>();
	private static HashMap<EventType, Boolean>					eventTypeRegistered	= initEventTypeRegistered();
	
	/**
	 * Inits the known {@link EventType}s as unregistered.
	 *
	 * @return the {@link HashMap} showing if the {@link EnventType}s are
	 * registered.
	 */
	private static HashMap<EventType, Boolean> initEventTypeRegistered() {
		final HashMap<EventType, Boolean> eventTypeRegistered = new HashMap<>();
		
		for(final EventType et : EventType.values())
			eventTypeRegistered.put(et, false);
		
		return eventTypeRegistered;
	}
	
	/**
	 * Checks if the {@link EventType} is registered.
	 *
	 * @param eventType
	 *     the {@link EventType} to check
	 * @return {@code true} if the {@link EventType} is registered.
	 */
	private boolean isEventTypeRegistered(final EventType eventType) {
		return eventTypeRegistered.get(eventType);
	}
	
	/**
	 * Gets the {@link EventListener}s for the {@link EventType}.
	 *
	 * @param eventType
	 *     the type
	 * @return the {@link EventListener}s for the {@link EventType} eventType
	 */
	private ArrayList<EventListener> getListenersForEventType(final EventType eventType) {
		return listener.get(eventType);
	}
	
	/**
	 * Handles the {@link Event}.
	 *
	 * @param event
	 *     the {@link Event} to handle
	 */
	public void handle(final Event event) {
		final EventType t = event.getEventType();
		
		if(!isEventTypeRegistered(t)) return;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(final EventListener l : getListenersForEventType(t))
					new Thread(new Runnable() {
						@Override
						public void run() {
							l.onEvent(event);
						}
					}, "Listener " + l + " handling " + t).start();
			}
		}, "Handling " + event).start();
	}
	
	/**
	 * Registers the {@link EventListener}.
	 *
	 * @param eventListener
	 *     the {@link EventListener} to register
	 */
	public void registerEventListener(final EventListener eventListener) {
		final EventType eT = eventListener.getEventType();
		
		if(!listener.containsKey(eT)) {
			listener.put(eT, new ArrayList<>());
			eventTypeRegistered.put(eT, true);
		}
		
		listener.get(eT).add(eventListener);
	}
}
