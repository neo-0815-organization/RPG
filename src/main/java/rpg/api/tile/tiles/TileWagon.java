package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileWagon extends Tile {
	
	public TileWagon() {
		sprite= new Sprite("tiles/wagon");
		sprite.addAnimation("wagon");
		sprite.setAnimation("wagon");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
