package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileCrystal extends Tile {
	
	public TileCrystal() {
		setHitbox(6);
		setSprite("crystal", "crystal");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
