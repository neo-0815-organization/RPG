package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileCrystal extends Tile {
	
	public TileCrystal() {
		sprite = new Sprite("tiles/crystal");
		sprite.addAnimation("crystal");
		sprite.setAnimation("crystal");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
