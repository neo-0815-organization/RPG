package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTreestumpSnowed extends Tile {

	public TileTreestumpSnowed() {
		sprite = new Sprite("tiles/treestump/snowed");
		sprite.addAnimation("treestumpSnowed");
		sprite.setAnimation("treestumpSnowed");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
