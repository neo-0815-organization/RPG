package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileWagon extends Tile {
	
	public TileWagon() {
		hitbox = new Hitbox(2, 1);
		sprite= new Sprite("tiles/wagon", SpriteTheme.MOERSBERGE);
		sprite.addAnimation("wagon");
		sprite.setAnimation("wagon");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
