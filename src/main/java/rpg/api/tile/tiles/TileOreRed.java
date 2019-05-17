package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreRed extends Tile {
	
	public TileOreRed() {
		sprite = new Sprite("tiles/ore_red");
		sprite.addAnimation("ore_red");
		sprite.setAnimation("ore_red");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
