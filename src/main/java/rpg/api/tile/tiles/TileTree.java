package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTree.TreeType;

public class TileTree extends TypedTile<TreeType> {
	
	public TileTree(final TreeType type) {
		super(type);
		
		setSprite("tree", type.name);
	}
	
	public enum TreeType implements TileType {
		NORMAL("tree"),
		APPLE("tree_apple"),
		APPLE_2("tree_apple2"),
		BIG_FACE("tree_big_face"),
		BIG("tree_big"),
		DEAD("tree_dead"),
		DEAD_2("tree_dead_2"),
		FIRE_SNOW("tree_fire_snow"),
		FIRE("tree_fire"),
		PALM("tree_palm");
		
		private final String name;
		
		private TreeType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
