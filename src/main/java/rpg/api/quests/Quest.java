package rpg.api.quests;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.filereading.RPGFileReader;
import rpg.api.localization.StringLocalizer;

/**
 * The class Quest
 * @author Tim Ludwig
 */
public class Quest {
	private int id, xp;
	private String title, text;
	private CharacterType type;
	private boolean repeatable;

	public Quest(int id, int xp, CharacterType type, boolean repeatable) {
		this.id = id;
		this.xp = xp;
		this.title = StringLocalizer.localize("quest." + id + ".title");
		this.text = StringLocalizer.localize("quest." + id + ".text");
		this.type = type;
		this.repeatable = repeatable;
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
