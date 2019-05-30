package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileBarrel extends Tile {
	
	public TileBarrel() {
		setHitbox(2);
		setSprite("barrel", "barrel");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
