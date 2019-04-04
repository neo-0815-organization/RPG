package rpg.api.quests;

import rpg.RPG;
import rpg.api.entity.Player;

public interface IReward {
	Player player = RPG.gameField.getPlayerController().getPlayer();
	public void rewardPlayer();
}
