package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBottle extends Tile {

	private BottleType type;
	
	public TileBottle(BottleType t) {
		type = t;
		sprite = new Sprite("tiles/bottle");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum BottleType{
		BEACH("beach/bottle_beach"),
		BEACH2("beach2/beach2"),
		HOLY_WATER("holyWater/bottle_water_holy"),
		MESSAGE("message/bottlemessage"),
		RIVER("river/bottle_river");
		
		public String name;
		
		private BottleType(String pName) {
			name = pName;
		}
	}
}
