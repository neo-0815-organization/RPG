package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromDungeon_IToM�rsbergMine extends TilePortal{

	public TilePortalFromDungeon_IToM�rsbergMine() {
		super("moersberge_mineshaft");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
