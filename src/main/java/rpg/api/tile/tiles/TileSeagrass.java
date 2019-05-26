package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileSeagrass extends Tile {
	
	public SeagrassType type;

	public TileSeagrass(SeagrassType t) {
		type = t;
		sprite = new Sprite("tiles/seagrass");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum SeagrassType{
		NORMAL("seagrass"),
		TYPE_2("seagrass2");
		
		private String name;
		
		private SeagrassType(String pName) {
			name = pName;
		}
	}
}
