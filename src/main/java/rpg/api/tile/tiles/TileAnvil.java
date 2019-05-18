package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileAnvil extends Tile {
	
	public TileAnvil() {
		sprite = new Sprite("tiles/anvil");
		sprite.addAnimation("anvil");
		sprite.setAnimation("anvil");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
