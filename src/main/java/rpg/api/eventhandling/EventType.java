package rpg.api.eventhandling;

public enum EventType {
	EVENT("event");
	
	String name;
	
	private EventType(final String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
}
