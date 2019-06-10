package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileShell.ShellType;

public class TileShell extends TypedTile<ShellType> {
	
	public TileShell(final ShellType type) {
		super(type);
		
		setSprite("shell", type.name);
	}
	
	public enum ShellType implements TileType {
		SPIKE("shell_spike"),
		TYPE_1("shell"),
		TYPE_2("shell2");
		
		private final String name;
		
		private ShellType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
