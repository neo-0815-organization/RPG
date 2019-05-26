package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTable extends Tile {
	
	private TableType type;

	public TileTable(TableType t) {
		type = t;
		hitbox = new Hitbox(type.width,type.height);
		sprite = new Sprite("tiles/" + type.name);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum TableType{
		NORMAL("table",4,2),
		LIGHT("table_light",2,1);
		
		private String name;
		private double width;
		private double height;
		
		private TableType(String pName, double w, double h) {
			name = pName;
			width = w;
			height = h;
		}
	}
	
}
