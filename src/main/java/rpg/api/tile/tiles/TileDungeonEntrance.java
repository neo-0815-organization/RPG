package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileDungeonEntrance extends Tile {
	
	public TileDungeonEntrance() {
		setHitbox(3);
		setSprite("dungeon_entrance", "dungeon_entrance");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
