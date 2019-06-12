package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTable.TableType;

public class TileTable extends TypedTile<TableType> {
	
	public TileTable(final TableType type) {
		super(type);
		
		setSprite("table", type.name);
	}
	
	public enum TableType implements TileType {
		NORMAL("table"),
		LIGHT("table_light");
		
		private final String name;
		
		private TableType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
