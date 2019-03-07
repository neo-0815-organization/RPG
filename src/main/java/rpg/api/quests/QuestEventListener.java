package rpg.api.quests;

import rpg.api.eventhandling.EventListener;
import rpg.api.eventhandling.events.Event;

public abstract class QuestEventListener implements EventListener{
	private boolean isTriggered;
	
	@Override
	public void onEvent(Event event) {
		isTriggered = true;
	}

	public boolean isTriggered() {
		return isTriggered;
	}
}
