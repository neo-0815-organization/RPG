package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBush extends Tile {
	
	public TileBush() {
		hitbox = new Hitbox(4, 2);
		
		sprite = new Sprite("tiles/bush");
		sprite.addAnimation("bush");
		sprite.setAnimation("bush");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
