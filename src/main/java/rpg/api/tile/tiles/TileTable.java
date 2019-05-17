package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTable extends Tile {
	
	public TileTable() {
		sprite = new Sprite("tiles/table");
		sprite.addAnimation("table");
		sprite.setAnimation("table");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
