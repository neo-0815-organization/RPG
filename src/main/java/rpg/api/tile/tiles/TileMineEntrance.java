package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileMineEntrance extends Tile {
	
	public TileMineEntrance() {
		setHitbox(8);
		setSprite("mine_entrance", "mine_entrance");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
