package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreGreen extends Tile{

	public TileOreGreen() {
		sprite = new Sprite("tiles/ore_green");
		sprite.addAnimation("ore_green");
		sprite.setAnimation("ore_green");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub
		
	}

}
