package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreGold extends Tile {

	public TileOreGold() {
		sprite = new Sprite("tiles/ore_gold");
		sprite.addAnimation("ore_gold");
		sprite.setAnimation("ore_gold");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
