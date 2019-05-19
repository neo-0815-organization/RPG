package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChairFront extends Tile {
	
	public TileChairFront() {
		sprite = new Sprite("tiles/chair");
		sprite.addAnimation("front");
		sprite.setAnimation("front");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
