package rpg.api.quests;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rpg.RPG;
import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.filereading.RPGFileReader;
import rpg.api.localization.StringLocalizer;
import rpg.api.scene.GameField;

/**
 * The class Quest
 * @author Tim Ludwig, Erik Diers, Jan Unterhuber, Alexander Schallenberg
 */
public class Quest {
	private int id;
	private String title, questInfo, map;
	private CharacterType type;
	private boolean repeatable, isFinished = false, inProgress = false;
	
	private IReward[] rewards;
	private QuestEventListener[] startListener, endListener;

	public Quest(int id, String map, CharacterType type, boolean repeatable, QuestEventListener[] startListener, QuestEventListener[] endListener, IReward[] rewards) {
		this.id = id;
		this.map = map;
		this.title = StringLocalizer.localize("quest." + id + ".title");
		this.questInfo = StringLocalizer.localize("quest." + id + ".text");
		this.type = type;
		this.repeatable = repeatable;
		
		this.startListener = startListener;
		this.endListener = endListener;
		this.rewards = rewards;
	}
	
	public boolean isAvailable() {
		return (!isFinished || repeatable) && !inProgress && RPG.gameField.background.getName().equals(map);
	}
	
	public boolean isTriggered() {
		int triggered = 0;
		for (QuestEventListener listener : startListener) {
			if (listener.isTriggered())triggered++;
		}
		return triggered == startListener.length;
	}
	
	public boolean isFinished() {
		return isFinished; //TODO right return-statement
	}
	
	public void finishQuest() {
		for (IReward r:rewards) 
			r.rewardPlayer();

		inProgress = false;
		isFinished = true;
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
