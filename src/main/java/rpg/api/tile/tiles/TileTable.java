package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTable extends Tile {
	
	private final TableType type;

	public TileTable(final TableType type) {
		this.type = type;
		
		hitbox = new Hitbox(type.width, type.height);
		sprite = new Sprite("tiles/" + type.name);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}

	public enum TableType{
		NORMAL("table", 4, 2),
		LIGHT("table_light", 2, 1);
		
		private final String name;
		private final double width;
		private final double height;
		
		private TableType(final String name, final double width, final double height) {
			this.name = name;
			this.width = width;
			this.height = height;
		}
	}
}
