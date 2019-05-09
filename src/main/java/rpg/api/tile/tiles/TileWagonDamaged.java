package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileWagonDamaged extends Tile {
	
	public TileWagonDamaged() {
		sprite= new Sprite("tiles/wagon_damaged",SpriteTheme.MOERSBERGE);
		sprite.addAnimation("wagon_damaged");
		sprite.setAnimation("wagon_damaged");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
