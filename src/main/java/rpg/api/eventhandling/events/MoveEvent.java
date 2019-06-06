package rpg.api.eventhandling.events;

import rpg.api.eventhandling.EventType;

public class MoveEvent extends Event {
	private char directionKey; // WASD

	public MoveEvent(char key) {
		super(EventType.MOVE_EVENT);
		this.directionKey = key;
	}
	
	public char getDirectionKey() {
		return directionKey;
	}

	
}
