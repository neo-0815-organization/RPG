package rpg.api.quests;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rpg.Logger;
import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.CharacterType;
import rpg.api.eventhandling.BundledListener;
import rpg.api.filehandling.RPGFileReader;
import rpg.api.localization.StringLocalizer;

/**
 * The class Quest
 * 
 * @author Tim Ludwig, Erik Diers, Jan Unterhuber, Alexander Schallenberg
 */
public class Quest {
	private final int id, idOfTheQuestBefore;
	private final String title;
	private String questInfo;
	private final String map;
	private final CharacterType type;
	private final boolean repeatable;
	private boolean isFinished = false;
	private boolean inProgress = false;
	
	private final IReward[] rewards;
	private final BundledListener startListener, endListener;
	
	public Quest(final int id, final int idOfTheQuestBefore, final String map, final CharacterType type, final boolean repeatable, final BundledListener startListener, final BundledListener endListener, final IReward... rewards) {
		this.id = id;
		this.idOfTheQuestBefore = idOfTheQuestBefore;
		this.map = map;
		title = StringLocalizer.localize("quest." + id + ".title");
		questInfo = StringLocalizer.localize("quest." + id + ".text");
		this.type = type;
		this.repeatable = repeatable;
		
		this.startListener = startListener;
		this.endListener = endListener;
		this.rewards = rewards;
	}
	
	public Quest setStartText(String unlocName) {
		questInfo = Statics.formatToWidth(StringLocalizer.localize(unlocName), 600, new Font("Arial", 0, 20), "<br>");
		return this;
	}
	
	/**
	 * Returns whether the Link{Quest} is avialable. !Does not call canStart().
	 * 
	 * @return {@code true} if the Link{Quest} is available
	 */
	public boolean isAvailable() {
		return (!isFinished || repeatable) && !inProgress && RPG.gameField.getBackground().getName().equals(map);
	}
	
	/**
	 * Returns whether the conditions for starting the Link{Quest} are met,
	 * !Does not call startQuest().
	 * 
	 * @return {@code true} if the Link{Quest} can start
	 */
	public boolean canStart() {
		return (idOfTheQuestBefore == -1 || QuestHandler.isQuestFinished(idOfTheQuestBefore)) &&  startListener.isTriggered();
	}
	
	/**
	 * Returns whether the conditions for finishing the Link{Quest} are met.
	 * !Does not call endQuest().
	 * 
	 * @return {@code true} if the Link{Quest} can end
	 */
	public boolean canEnd() {
		return endListener.isTriggered();
	}
	
	/**
	 * Finishes the Link{Quest} off and rewards the player.
	 */
	public void finishQuest() {
		Logger.debug("Quest has been finished");
		for(final IReward r : rewards)
			r.rewardPlayer();
		
		inProgress = false;
		isFinished = true;
	}
	
	/**
	 * Returns whether the Link{Quest} is inProgress.
	 * 
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
		RPG.getPlayer().getInventory().getRenderer().setText(questInfo);
		Logger.debug("Quest has been started"); 
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
	 * 
	 * @return {@code true} if the Link{Quest} was finished at least once
	 */
	public boolean isFinished() {
		return isFinished;
	}
	
	public int getID() {
		return id;
	}
	
	public static class Loader {
		public static Quest loadQuest(final String path, final String[] strings) {
			
			return null;
		}
		
		public static List<Quest> loadQuests(final String path) {
			final Map<String, String[]> questLines = RPGFileReader.readLineMultiSplit(path, ":", 6);
			final List<Quest> quests = new LinkedList<>();
			
			for(final String questId : questLines.keySet())
				quests.add(loadQuest(questId, questLines.get(questId)));
			
			return quests;
		}
	}
}
