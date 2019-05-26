package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;


public class TileCrystal extends Tile {

	public TileCrystal() {
		hitbox = new Hitbox(6,6);
		sprite = new Sprite("tiles/crystal");
		sprite.addAnimation("crystal");
		sprite.setAnimation("crystal");
	}
	
	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
