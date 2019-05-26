package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileComputer extends Tile {

	public TileComputer() {
		hitbox = new Hitbox(1,1);
		sprite = new Sprite("tiles/computer");
		sprite.addAnimation("computer");
		sprite.setAnimation("computer");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
