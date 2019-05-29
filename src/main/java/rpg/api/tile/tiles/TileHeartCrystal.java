package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileHeartCrystal extends Tile {

	public TileHeartCrystal() {
		hitbox = new Hitbox(6, 8);
		sprite = new Sprite("tiles/heart_crystal");
		sprite.addAnimation("heart_crystal");
		sprite.setAnimation("heart_crystal");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
