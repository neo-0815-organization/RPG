package rpg.api.quests;

import java.util.ArrayList;
import java.util.List;
/**
 * QuestHandler handles and updates all quests.
 * It contains only static functions and is unable to instantiate.
 * @author Erik Diers, Jan Unterhuber, Alexander Schallenberg
 *
 */
public class QuestHandler {
	private static List<Quest> allQuests = new ArrayList<>();
	
	/**
	 * Updating the QuestHandler means updating all quests.
	 */
	public static void update() {
		for (Quest quest : allQuests) {
			if (quest.isInProgress()) {
				if (quest.canEnd()) quest.finishQuest();
			} else {
				if (quest.isAvailable() && quest.canStart())quest.startQuest();
			}
			quest.resetListerner();
		}
	}
	/**
	 * Adds a new quest to the list of allQuests.
	 * @param newQuest
	 */
	public static void addQuest(Quest newQuest) {
		allQuests.add(newQuest);
	}
	/**
	 * Checks whether a specific quest has been finished at least once.
	 * @param questID The questID of the specific quest
	 * @return Link{Boolean} Whether the quest has been finished or not.
	 */
	public static boolean isQuestFinished(int questID) {
		for (Quest q : allQuests) {
			if (q.getID() == questID)return q.isFinished();
		}
		return false;
	}

}
