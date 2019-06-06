package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileWorkbench extends Tile {

	public TileWorkbench() {
		setSprite("workbench", "workbench");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
