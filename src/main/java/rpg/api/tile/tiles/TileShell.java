package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileShell extends Tile {
	
	private ShellType type;

	public TileShell(ShellType t) {
		type = t;
		sprite = new Sprite("tiles/shell");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum ShellType{
		SPIKE("spike/shell_spike"),
		TYPE_1("normal/shell"),
		TYPE_2("normal/shell2");
		
		private String name;
		
		private ShellType(String pName) {
			name = pName;
		}
	}
	
}
