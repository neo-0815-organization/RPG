package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileClock extends Tile {
	
	public TileClock() {
		setHitbox(1);
		setSprite("clock", "clock");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
