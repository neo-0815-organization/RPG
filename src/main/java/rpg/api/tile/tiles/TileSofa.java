package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileSofa extends Tile {

	public TileSofa() {
		setSprite("sofa", "sofa");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
