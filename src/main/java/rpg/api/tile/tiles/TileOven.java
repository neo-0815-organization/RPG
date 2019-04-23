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
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
