package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileSmeltery extends Tile {

	public TileSmeltery() {
		hitbox = new Hitbox(2, 2);
		sprite = new Sprite("tiles/smeltery", SpriteTheme.SMELTERY);
		sprite.addAnimation("smeltery");
		sprite.setAnimation("smeltery");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
