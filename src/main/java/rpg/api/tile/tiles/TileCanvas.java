package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileCanvas extends Tile {
	
	public TileCanvas() {
		setHitbox(0.5, 0.5);
		setSprite("canvas", "canvas_sunflower");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
