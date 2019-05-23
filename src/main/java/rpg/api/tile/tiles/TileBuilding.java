package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBuilding extends Tile {

	public TileBuilding() {
		sprite = new Sprite("tiles/buildings");
		sprite.addAnimation("buildings/house");
		sprite.setAnimation("buildings/house");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
