package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileAnvil extends Tile {
	
	public TileAnvil() {
		hitbox = new Hitbox(1, 1);
		sprite = new Sprite("tiles/anvil", SpriteTheme.SMITHERY);
		sprite.addAnimation("anvil");
		sprite.setAnimation("anvil");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
}
