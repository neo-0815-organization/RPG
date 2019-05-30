package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileSofa extends Tile {
	
	public TileSofa() {
		setHitbox(3, 1.5);
		setSprite("sofa", "sofa");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
