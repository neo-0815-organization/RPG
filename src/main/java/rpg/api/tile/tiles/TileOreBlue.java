package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreBlue extends Tile {

	public TileOreBlue() {
		sprite = new Sprite("tiles/ore_blue");
		sprite.addAnimation("ore_blue");
		sprite.setAnimation("ore_blue");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
