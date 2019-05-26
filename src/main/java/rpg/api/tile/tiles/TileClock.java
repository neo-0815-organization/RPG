package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileClock extends Tile {

	public TileClock() {
		hitbox = new Hitbox(1,1);
		sprite = new Sprite("tiles/clock");
		sprite.addAnimation("clock");
		sprite.setAnimation("clock");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
