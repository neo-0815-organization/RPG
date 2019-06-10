package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTent.TentType;

public class TileTent extends TypedTile<TentType> {
	
	public TileTent(final TentType type) {
		super(type);
		
		setSprite("tent", type.name);
	}
	
	public enum TentType implements TileType {
		NORMAL("tent_normal"),
		RED("tent_red"),
		YELLOW("tent_yellow");
		
		private final String name;
		
		private TentType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
