package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;
import rpg.api.tile.Tile;

public class TilePortal extends Tile {
	
	String destination;

	public TilePortal(String destination) {
		super();
		this.destination = destination;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}

}
