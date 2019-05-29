package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileFurnace extends Tile {

	public TileFurnace() {
		hitbox = new Hitbox(2, 2);
		sprite = new Sprite("tiles/furnace");
		sprite.addAnimation("furnace");
		sprite.setAnimation("furnace");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
