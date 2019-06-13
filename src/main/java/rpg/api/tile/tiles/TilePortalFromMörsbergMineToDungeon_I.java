package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromMörsbergMineToDungeon_I extends TilePortal {

	public TilePortalFromMörsbergMineToDungeon_I() {
		super("dungeon_i");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
