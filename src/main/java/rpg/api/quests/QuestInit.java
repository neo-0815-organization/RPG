package rpg.api.quests;

import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.BundledListener;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.Event;

public class QuestInit {
	public static void initQuests() {
		QuestHandler.addQuest(new Quest(0, -1, "Kristallebene", CharacterType.NONE, false, new BundledListener(new BundledListener.EventCondition() {
			@Override
			public boolean eventTriggered(Event e) {
				return true;
			}
			
			@Override
			public EventType getEventType() {
				return EventType.CURRENT_MAP_EVENT;
			}
		}
		) 
			
		, new BundledListener(new BundledListener.PressWASD()), new IReward() {
			
			@Override
			public void rewardPlayer() {
				player.addXP(10);
			}
		}).setStartText("quest.0.info"));
	}
}
