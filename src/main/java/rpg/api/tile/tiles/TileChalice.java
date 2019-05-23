package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChalice extends Tile {

	public TileChalice() {
		sprite = new Sprite("tiles/chalice");
		sprite.addAnimation("chalice");
		sprite.setAnimation("chalice");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
