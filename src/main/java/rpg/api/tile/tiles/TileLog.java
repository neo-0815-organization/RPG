package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileLog extends Tile {
	
	private LogType type;

	public TileLog(LogType t) {
		type = t;
		hitbox = new Hitbox(2,1);
		sprite = new Sprite("tiles/log");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}
	
	public enum LogType{
		LEFT_SIDE("log1"),
		NORMAL("log2"),
		NORMAL_2("log3");
		
		private String name;
		
		private LogType(String pName) {
			name = pName;
		}
	}
	
}
