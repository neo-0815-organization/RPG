package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileSmeltery extends Tile {

	public TileSmeltery() {
		setSprite("smeltery", SpriteTheme.SMELTERY, "smeltery");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}
}
