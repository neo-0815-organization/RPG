package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TileDungeonEntrance extends TilePortal {
	
	public TileDungeonEntrance() {
		super("seyhan");
		setSprite("dungeon_entrance", "dungeon_entrance");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
