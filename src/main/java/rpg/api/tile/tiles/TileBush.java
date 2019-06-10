package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileBush extends Tile {

	public TileBush() {
		setSprite("bush", "bush");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
