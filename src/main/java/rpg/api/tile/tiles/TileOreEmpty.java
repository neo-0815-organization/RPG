package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreEmpty extends Tile {

	public TileOreEmpty() {
		sprite = new Sprite("tiles/ore_empty");
		sprite.addAnimation("ore_empty");
		sprite.setAnimation("ore_empty");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
