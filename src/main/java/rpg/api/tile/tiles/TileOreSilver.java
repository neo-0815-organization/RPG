package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreSilver extends Tile {
	
	public TileOreSilver() {
		sprite = new Sprite("tiles/ore_silver");
		sprite.addAnimation("ore_silver");
		sprite.setAnimation("ore_silver");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
