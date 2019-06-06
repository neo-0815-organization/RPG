package rpg.api.eventhandling.events;

import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventType;

public class SpeakEvent extends Event{
	private Entity speakWith;
	
	public SpeakEvent(Entity speakWith) {
		super(EventType.SPEAK_EVENT);
		this.speakWith = speakWith;
	}
	
	public Entity getSpeakpartner() {
		return speakWith;
	}

}
