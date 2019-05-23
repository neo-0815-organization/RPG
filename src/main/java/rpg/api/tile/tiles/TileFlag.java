package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileFlag extends Tile {
	
	public FlagType type;

	public TileFlag(FlagType t) {
		type = t;
		sprite = new Sprite("tiles/flag");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum FlagType{
		RED("red/flag_red"),
		BLUE("blue/flag_blue"),
		YELLOW("yellow/flag_yellow");
		
		private String name;
		
		private FlagType(String pName) {
			name = pName;
		}
	}
}
