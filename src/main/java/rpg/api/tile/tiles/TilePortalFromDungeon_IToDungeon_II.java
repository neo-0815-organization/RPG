package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromDungeon_IToDungeon_II extends TilePortal {

	public TilePortalFromDungeon_IToDungeon_II() {
		super("Dungeon_II");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
