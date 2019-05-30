package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileOven extends Tile {
	
	public TileOven() {
		setHitbox(2);
		setSprite("oven", "oven");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
