package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;

public class TilePortalFromBrombachToCrystalPlain extends TilePortal{

	public TilePortalFromBrombachToCrystalPlain() {
		super("crystal_flatlands");
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		RPG.gameField.save.background = new Background(destination);
	}
}
