package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;
import rpg.api.tile.Tile;

public class TileMineEntrance extends TilePortal {
	
	public TileMineEntrance() {
		super("moersberg_mineshaft");
		setSprite("mine_entrance", "mine_entrance");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
