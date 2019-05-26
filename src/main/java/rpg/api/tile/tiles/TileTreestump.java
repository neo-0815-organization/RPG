package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTreestump extends Tile {

	public TileTreestump() {
		hitbox = new Hitbox(3,3);
		sprite = new Sprite("tiles/treestump");
		sprite.addAnimation("treestump");
		sprite.setAnimation("treestump");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
