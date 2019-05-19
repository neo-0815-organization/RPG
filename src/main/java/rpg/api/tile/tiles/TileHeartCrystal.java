package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileHeartCrystal extends Tile {
	
	public TileHeartCrystal() {
		sprite = new Sprite("tiles/heart_crystal");
		sprite.addAnimation("heart_crystal");
		sprite.setAnimation("heart_crystal");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
