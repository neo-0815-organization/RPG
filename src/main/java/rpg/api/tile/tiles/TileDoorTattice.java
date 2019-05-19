package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileDoorTattice extends Tile {
	
	public TileDoorTattice() {
		sprite = new Sprite("tiles/door_tattice");
		sprite.addAnimation("door_tattice");
		sprite.setAnimation("door_tattice");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
