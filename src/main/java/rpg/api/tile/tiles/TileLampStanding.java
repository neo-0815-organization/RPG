package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.Tile;

public class TileLampStanding extends Tile {
	
	public TileLampStanding() {
		setHitbox(0.25, 1);
		setSprite("lamp_standing", "lamp_standing");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
