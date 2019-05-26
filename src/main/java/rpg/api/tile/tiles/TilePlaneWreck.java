package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TilePlaneWreck extends Tile {

	public TilePlaneWreck() {
		hitbox = new Hitbox(6,6);
		sprite = new Sprite("tiles/planeWreck");
		sprite.addAnimation("plane_wreck");
		sprite.setAnimation("plane_wreck");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
