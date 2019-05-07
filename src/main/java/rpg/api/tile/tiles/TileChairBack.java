package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChairBack extends Tile {

	public TileChairBack() {
		sprite = new Sprite("tiles/chair");
		sprite.addAnimation("back");
		sprite.setAnimation("back");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
