package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChest extends Tile {
	
	public TileChest() {
		sprite = new Sprite("tiles/chest");
		sprite.addAnimation("chest");
		sprite.setAnimation("chest");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
