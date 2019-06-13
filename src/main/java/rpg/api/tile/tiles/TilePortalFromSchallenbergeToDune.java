package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromSchallenbergeToDune extends TilePortal{

	public TilePortalFromSchallenbergeToDune() {
		super("bay_of_the_stranded");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
