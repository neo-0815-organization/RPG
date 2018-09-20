package rpg.api.eventhandling;

import java.util.HashMap;

public enum EventType {
	EVENT(0);
	
	private static HashMap<Integer, EventType> idMap = new HashMap<>();
		
	int eventID;
	
	private EventType(int id) {
		this.eventID = id;
		
		addEventType(this, id);
	}
	
	private void addEventType(EventType eventType, int id) {
		idMap.put(id, eventType);
	}
	
	public static EventType getEventTypeByID(int id) {
		return idMap.get(id);
	}
	
	public int getID() {
		return eventID;
	}
}
