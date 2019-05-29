package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileSofa extends Tile {

	public TileSofa() {
		hitbox = new Hitbox(3, 1.5);
		sprite = new Sprite("tiles/sofa");
		sprite.addAnimation("sofa");
		sprite.setAnimation("sofa");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
