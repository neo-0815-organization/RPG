package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileMineEntrance extends Tile {

	public TileMineEntrance() {
		hitbox = new Hitbox(8,8);
		sprite = new Sprite("tiles/mine_entrance");
		sprite.addAnimation("mine_entrance");
		sprite.setAnimation("mine_entrance");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
