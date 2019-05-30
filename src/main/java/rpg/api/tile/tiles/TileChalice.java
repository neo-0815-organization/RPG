package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileChalice extends Tile {
	
	public TileChalice() {
		setHitbox(0.5);
		setSprite("chalice", "chalice");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
