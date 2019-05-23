package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileCanvas extends Tile {

	public TileCanvas() {
		sprite = new Sprite("tiles/canvas");
		sprite.addAnimation("canvas_sunflower");
		sprite.setAnimation("canvas_sunflower");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
