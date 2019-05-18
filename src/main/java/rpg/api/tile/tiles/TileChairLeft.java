package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChairLeft extends Tile {
	
	public TileChairLeft() {
		sprite = new Sprite("tiles/chair");
		sprite.addAnimation("left");
		sprite.setAnimation("left");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
