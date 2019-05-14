package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileLog extends Tile {

	public TileLog() {
		sprite = new Sprite("tiles/log");
		sprite.addAnimation("log1");
		sprite.addAnimation("log2");
		sprite.addAnimation("log3");
		sprite.setAnimation("log1");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
