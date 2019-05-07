package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTentYellow extends Tile {

	public TileTentYellow() {
		sprite = new Sprite("tiles/tent_yellow");
		sprite.addAnimation("tent_yellow");
		sprite.setAnimation("tent_yellow");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
