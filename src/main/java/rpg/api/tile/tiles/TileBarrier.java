package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileBarrier extends Tile {
	
	public TileBarrier() {
		hitbox = new Hitbox(1, 1);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
