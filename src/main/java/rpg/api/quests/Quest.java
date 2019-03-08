package rpg.api.quests;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.filehandling.RPGFileReader;

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
	
	public static class Loader {
		public static Quest loadQuest(String path, String[] strings) {
			
			return null;
		}
		
		public static List<Quest> loadQuests(String path) {
			Map<String, String[]> questLines = RPGFileReader.readLineMultiSplit(path, ":", 6);
			List<Quest> quests = new LinkedList<>();
			
			for(String questId : questLines.keySet()) {
				quests.add(loadQuest(questId, questLines.get(questId)));
			}
			
			return quests;
		}
	}
}
