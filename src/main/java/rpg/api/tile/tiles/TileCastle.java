package rpg.api.tile.tiles;

import rpg.RPG;
import rpg.api.entity.Player;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;

public class TileCastle extends TilePortal {
	
	public TileCastle() {
		super("castle", 22, 32);
		
		setSprite("castle", SpriteTheme.DWARF_CITY, "castle");
	}
}
