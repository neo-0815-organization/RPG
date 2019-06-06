package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileLampStanding extends Tile {

	public TileLampStanding() {
		setSprite("lamp_standing", "lamp_standing");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
