package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTable.TableType;

public class TileTable extends TypedTile<TableType> {
	
	public TileTable(final TableType type) {
		super(type);
		
		setHitbox(type.width, type.height);
		setSprite("table", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum TableType implements TileTypeSized {
		NORMAL("table", 4, 2),
		LIGHT("table_light", 2, 1);
		
		private final String name;
		private final double width, height;
		
		private TableType(final String name, final double width, final double height) {
			this.name = name;
			this.width = width;
			this.height = height;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public double getWidth() {
			return width;
		}
		
		@Override
		public double getHeight() {
			return height;
		}
	}
}
