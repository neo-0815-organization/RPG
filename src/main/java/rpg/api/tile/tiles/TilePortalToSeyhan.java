package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.scene.Background;
import rpg.api.tile.Tile;

public class TilePortalToSeyhan extends TilePortal {
	
	public TilePortalToSeyhan() {
		super("schallenberge");
		setSprite("portal_to_seyhan", SpriteTheme.SCHALLENBERGE, "portal_to_seyhan");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
