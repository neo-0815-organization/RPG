package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileWagonDamaged extends Tile {
	
	public TileWagonDamaged() {
		sprite = new Sprite("tiles/wagon_damaged");
		sprite.addAnimation("wagon_damaged");
		sprite.setAnimation("wagon_damaged");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
