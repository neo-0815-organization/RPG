package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TilePortalToSeyhan extends Tile {
	
	public TilePortalToSeyhan() {
		sprite = new Sprite("tiles/portal_to_seyhan", SpriteTheme.SCHALLENBERGE);
		sprite.addAnimation("portal_to_seyhan");
		sprite.setAnimation("portal_to_seyhan");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
