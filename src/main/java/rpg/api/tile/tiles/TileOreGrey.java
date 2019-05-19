package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreGrey extends Tile {
	
	public TileOreGrey() {
		sprite = new Sprite("tiles/ore_grey");
		sprite.addAnimation("ore_grey");
		sprite.setAnimation("ore_grey");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
