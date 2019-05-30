package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileBarrier extends Tile {
	
	public TileBarrier() {
		setHitbox(1);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
