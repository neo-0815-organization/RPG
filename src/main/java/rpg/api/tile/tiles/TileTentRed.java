package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTentRed extends Tile {
	
	public TileTentRed() {
		sprite = new Sprite("tiles/tent_red");
		sprite.addAnimation("tent_red");
		sprite.setAnimation("tent_red");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
