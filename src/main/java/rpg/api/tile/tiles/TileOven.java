package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOven extends Tile {
	
	public TileOven() {
		sprite = new Sprite("tiles/oven");
		sprite.addAnimation("oven");
		sprite.setAnimation("oven");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
