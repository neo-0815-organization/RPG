package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TilePlaneWreck extends Tile {

	public TilePlaneWreck() {
		setSprite("plane_wreck", "plane_wreck");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
