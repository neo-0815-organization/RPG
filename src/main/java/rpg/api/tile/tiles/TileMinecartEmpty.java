package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileMinecartEmpty extends Tile {
	
	public TileMinecartEmpty() {
		sprite = new Sprite("tiles/minecart_empty");
		sprite.addAnimation("minecart_empty");
		sprite.setAnimation("minecart_empty");
		
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
