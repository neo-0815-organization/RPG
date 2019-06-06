package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileFurnace extends Tile {

	public TileFurnace() {
		setSprite("furnace", "furnace");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
