package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TilePortalToSeyhan extends Tile {
	
	public TilePortalToSeyhan() {
		setHitbox(3, 2);
		setSprite("portal_to_seyhan", SpriteTheme.SCHALLENBERGE, "portal_to_seyhan");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
