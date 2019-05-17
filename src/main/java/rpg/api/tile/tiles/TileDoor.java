package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileDoor extends Tile {
	
	public TileDoor() {
		sprite = new Sprite("tiles/door");
		sprite.addAnimation("door");
		sprite.setAnimation("door");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
