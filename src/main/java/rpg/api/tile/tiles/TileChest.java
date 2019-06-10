package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileChest extends Tile {

	public TileChest() {
		setSprite("chest", "chest");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
