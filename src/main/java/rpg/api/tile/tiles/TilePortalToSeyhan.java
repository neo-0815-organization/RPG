package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TilePortalToSeyhan extends Tile {
	
	public TilePortalToSeyhan() {
		sprite = new Sprite("tiles/portal_to_seyhan");
		sprite.addAnimation("portal_to_seyhan");
		sprite.setAnimation("portal_to_seyhan");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
