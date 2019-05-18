package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileFurnace extends Tile {
	
	public TileFurnace() {
		sprite = new Sprite("tiles/furnace");
		sprite.addAnimation("furnace");
		sprite.setAnimation("furnace");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
