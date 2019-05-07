package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileCauldron extends Tile {

	public TileCauldron() {
		sprite = new Sprite("tiles/cauldron");
		sprite.addAnimation("cauldron");
		sprite.setAnimation("cauldron");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
