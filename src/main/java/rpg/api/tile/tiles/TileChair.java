package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileChair extends Tile {
	
	private ChairType type;

	public TileChair(ChairType c) {
		type = c;
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
		
		public String type;
		
		private ChairType(String type) {
			this.type = type;
		}
	}
}
