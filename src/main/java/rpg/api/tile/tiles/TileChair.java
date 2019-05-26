package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChair extends Tile {
	
	private ChairType type;

	public TileChair(ChairType c) {
		type = c;
		hitbox = new Hitbox(4,4);
		sprite = new Sprite("tiles/chair");
		sprite.addAnimation(type.type);
		sprite.setAnimation(type.type);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum ChairType{
		FRONT("front"),
		LEFT("left"),
		RIGHT("right"),
		BACK("normal");
		
		private String type;
		
		private ChairType(String type) {
			this.type = type;
		}
	}
}
