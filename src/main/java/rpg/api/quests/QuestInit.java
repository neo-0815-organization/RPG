package rpg.api.quests;

import rpg.RPG;
import rpg.api.entity.CharacterSheet;
import rpg.api.entity.CharacterType;
import rpg.api.entity.Person;
import rpg.api.eventhandling.BundledListener;
import rpg.api.eventhandling.EventType;
import rpg.api.eventhandling.events.Event;
import rpg.api.gfx.menus.MenuProlog;
import rpg.api.scene.GameField;
import rpg.api.vector.ModifiableVec2D;

public class QuestInit {
	public static void initQuests() {
		QuestHandler.addQuest(new Quest(0, -1, "crystal_flatlands", CharacterType.NONE, false, new BundledListener(new BundledListener.EventCondition() {
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
		
		QuestHandler.addQuest(new Quest(1, 0, "crystal_flatlands", CharacterType.NONE, false, new BundledListener(new BundledListener.EventCondition() {
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
			
		, new BundledListener(new BundledListener.SpeakTo("Heinz")), new IReward() {
			
			@Override
			public void rewardPlayer() {
				player.addXP(10);
				new MenuProlog(40, false).show();
			}
		}).setStartText("quest.1.info"));
		
	}
	
	public static void initPersons() {
		GameField gameField = RPG.gameField;
		
		switch(RPG.gameField.getBackground().getName()) {
		case "crystal_flatlands":
			gameField.addEntity(new Person("Heinz", CharacterSheet.DRAFT_GUARFIAN, 0, "credtisdialog", ModifiableVec2D.createXY(10, 5)));
			break;
		}
	}
}
