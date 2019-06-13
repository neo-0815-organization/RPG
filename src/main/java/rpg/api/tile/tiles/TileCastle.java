package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.entity.Player;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;
import rpg.api.vector.UnmodifiableVec2D;

public class TileCastle extends Tile {
	
	public TileCastle() {
		setSprite("castle", SpriteTheme.DWARF_CITY, "castle");
	}
	
	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		if(eventType == EventType.COLLISION_EVENT && objects[1] instanceof Player) {
			RPG.gameField.save.changeBackground("castle", 22, 32);
		}
	}
}
