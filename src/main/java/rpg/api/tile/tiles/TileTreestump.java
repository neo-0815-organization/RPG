package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTreestump.TreestumpType;

public class TileTreestump extends TypedTile<TreestumpType> {
	
	public TileTreestump(final TreestumpType type) {
		super(type);
		
		setSprite("treestump", type.name);
	}
	
	public enum TreestumpType implements TileType {
		NORMAL("treestump"),
		SNOWED("treestump_snowed");
		
		private final String name;
		
		private TreestumpType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
