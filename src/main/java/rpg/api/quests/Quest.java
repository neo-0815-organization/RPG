package rpg.api.quests;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rpg.RPG;
import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.BundledListener;
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
	private BundledListener startListener, endListener;

	public Quest(int id, String map, CharacterType type, boolean repeatable, BundledListener startListener, BundledListener endListener, IReward[] rewards) {
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
	
	/**
	 * Returns whether the Link{Quest} is avialable.
	 * !Does not call canStart().
	 * @return {@code true} if the Link{Quest} is available
	 */
	public boolean isAvailable() {
		return (!isFinished || repeatable) && !inProgress && RPG.gameField.background.getName().equals(map);
	}
	
	/**
	 * Returns whether the conditions for starting the Link{Quest} are met,
	 * !Does not call startQuest().
	 * @return {@code true} if the Link{Quest} can start
	 */
	public boolean canStart() {
		return startListener.isTriggered();
	}
	
	/**
	 * Returns whether the conditions for finishing the Link{Quest} are met.
	 * !Does not call endQuest().
	 * @return {@code true} if the Link{Quest} can end
	 */
	public boolean canEnd() {
		return endListener.isTriggered();
	}
	
	/**
	 * Finishes the Link{Quest} off and rewards the player.
	 */
	public void finishQuest() {
		for (IReward r:rewards) 
			r.rewardPlayer();

		inProgress = false;
		isFinished = true;
	}
	
	/**
	 * Returns whether the Link{Quest} is inProgress.
	 * @return {@code true} if the Link{Quest} is in progress
	 */
	public boolean isInProgress() {
		return inProgress;
	}
	
	/**
	 * Starts the Link{Quest}
	 */
	public void startQuest() {
		inProgress = true;
		System.out.println("Quest has been started");
	}
	
	/**
	 * Resets all listener belonging to this Link{Quest}
	 */
	public void resetListerner() {
		startListener.reset();
		endListener.reset();
	}
	
	/**
	 * Returns whether the Link{Quest} was finished at leat once
	 * @return {@code true} if the Link{Quest} was finished at least once
	 */
	public boolean isFinished() {
		return isFinished;
	}
	
	public int getID() {
		return id;
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
