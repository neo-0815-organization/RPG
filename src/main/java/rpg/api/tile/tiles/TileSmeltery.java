package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileSmeltery extends Tile {
	
	public TileSmeltery() {
		sprite = new Sprite("tiles/smeltery");
		sprite.addAnimation("smeltery");
		sprite.setAnimation("smeltery");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
