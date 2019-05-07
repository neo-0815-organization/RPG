package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTent extends Tile {

	public TileTent() {
		sprite = new Sprite("tiles/oven");
		sprite.addAnimation("tent");
		sprite.setAnimation("tent");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
