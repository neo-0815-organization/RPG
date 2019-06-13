package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;
import rpg.api.tile.Tile;

public class TilePortal extends Tile {
	private final String destination;
	private final double x;
	private final double y;

	public TilePortal(String destination, double x, double y) {
		this.destination = destination;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.changeBackground(destination);
	}
}
