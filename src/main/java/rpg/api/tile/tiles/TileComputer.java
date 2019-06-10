package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileComputer extends Tile {

	public TileComputer() {
		setSprite("computer", "computer");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
