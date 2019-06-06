package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileCauldron extends Tile {

	public TileCauldron() {
		setSprite("cauldron", "cauldron");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
