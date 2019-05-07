package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBarrel extends Tile {
	
	public TileBarrel() {
		sprite = new Sprite("tiles/barrel");
		sprite.addAnimation("barrel");
		sprite.setAnimation("barrel");
		
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub
		
	}
	
}
