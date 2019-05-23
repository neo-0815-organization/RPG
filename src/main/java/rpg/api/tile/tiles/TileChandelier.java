package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChandelier extends Tile {

	public TileChandelier() {
		sprite = new Sprite("tiles/chandelier");
		sprite.addAnimation("chandelier");
		sprite.setAnimation("chandelier");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
