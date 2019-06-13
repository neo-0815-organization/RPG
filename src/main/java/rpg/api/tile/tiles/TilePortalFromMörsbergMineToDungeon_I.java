package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromM�rsbergMineToDungeon_I extends TilePortal {

	public TilePortalFromM�rsbergMineToDungeon_I() {
		super("dungeon_i");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
