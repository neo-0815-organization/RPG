package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromDungeon_IIToDungeon_I extends TilePortal{

	public TilePortalFromDungeon_IIToDungeon_I() {
		super("Dungeon_I");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
