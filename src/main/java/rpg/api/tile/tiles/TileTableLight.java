package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTableLight extends Tile {

	public TileTableLight() {
		sprite = new Sprite("tiles/table_light");
		sprite.addAnimation("table_light");
		sprite.setAnimation("table_light");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
