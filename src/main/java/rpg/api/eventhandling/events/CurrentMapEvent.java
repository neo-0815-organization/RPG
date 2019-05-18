package rpg.api.eventhandling.events;

import rpg.RPG;
import rpg.api.eventhandling.EventType;

public class CurrentMapEvent extends Event{
	private String worldname;
	
	public CurrentMapEvent() {
		this(RPG.gameField.getBackground().getName());
	}
	
	public CurrentMapEvent(String worldname) {
		super(EventType.CURRENT_MAP_EVENT);
		this.worldname = worldname;
	}
	
	public String getWorldname() {
		return worldname;
	}
	
}
