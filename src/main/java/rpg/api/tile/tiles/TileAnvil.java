package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileAnvil extends Tile {
	
	public TileAnvil() {
		setHitbox(1);
		setSprite("anvil", SpriteTheme.SMITHERY, "anvil");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
