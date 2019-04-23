package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileWorkbench extends Tile {

	public TileWorkbench() {
		sprite = new Sprite("tiles/workbench");
		sprite.addAnimation("workbench");
		sprite.setAnimation("workbench");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
