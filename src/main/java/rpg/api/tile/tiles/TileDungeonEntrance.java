package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileDungeonEntrance extends Tile {

	public TileDungeonEntrance() {
		sprite = new Sprite("tiles/dungeon_entrance");
		sprite.addAnimation("dungeon_entrance");
		sprite.setAnimation("dungeon_entrance");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub
		
	}

}
