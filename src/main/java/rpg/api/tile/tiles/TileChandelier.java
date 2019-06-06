package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileChandelier extends Tile {

	public TileChandelier() {
		setSprite("chandelier", "chandelier");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
