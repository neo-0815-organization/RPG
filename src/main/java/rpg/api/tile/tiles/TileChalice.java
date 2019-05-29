package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChalice extends Tile {

	public TileChalice() {
		hitbox = new Hitbox(0.5,0.5);
		sprite = new Sprite("tiles/chalice");
		sprite.addAnimation("chalice");
		sprite.setAnimation("chalice");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
