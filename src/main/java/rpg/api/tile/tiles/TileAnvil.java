package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileAnvil extends Tile {
	
	public TileAnvil() {
		sprite = new Sprite("tiles/anvil");
		sprite.addAnimation("anvil");
		sprite.setAnimation("anvil");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
