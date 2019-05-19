package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChairRight extends Tile {
	
	public TileChairRight() {
		sprite = new Sprite("tiles/chair");
		sprite.addAnimation("right");
		sprite.setAnimation("right");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
