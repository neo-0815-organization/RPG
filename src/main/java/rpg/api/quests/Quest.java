package rpg.api.quests;

import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.EventTrigger;

public class Quest {
private int id, xp;
private String title, text;
private CharacterType type;
private boolean repeatable;
private EventTrigger startTrigger, endTrigger;

public Quest(int id, int xp, String title, String text, CharacterType type, boolean repeatable,	EventTrigger startTrigger, EventTrigger endTrigger) {
		this.id = id;
		this.xp = xp;
		this.title = title;
		this.text = text;
		this.type = type;
		this.repeatable = repeatable;
		this.startTrigger = startTrigger;
		this.endTrigger = endTrigger;
	}
}
