package rpg.api.eventhandling;

import java.util.ArrayList;
import java.util.HashMap;

import rpg.api.eventhandling.events.Event;

public class EventHandler {
	private static HashMap<EventType, ArrayList<EventListener>> listener = new HashMap<>();
	
	public void handle(final Event e) {
		final EventType t = e.getEventType();
		
		if(!listener.containsKey(t)) return;
		
		for(final EventListener l : listener.get(t))
			new Thread(new Runnable() {
				@Override
				public void run() {
					l.onEvent(e);
				}
			}, "");
	}
	
	public void registerEventListener(final EventListener l) {
		EventType eT = l.getEventType();
		
		if(!listener.containsKey(eT)) listener.put(eT, new ArrayList<>());
			
		listener.get(eT).add(l);
	}
}
