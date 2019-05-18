package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileComputer extends Tile {
	
	public TileComputer() {
		sprite = new Sprite("tiles/computer");
		sprite.addAnimation("computer");
		sprite.setAnimation("computer");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
