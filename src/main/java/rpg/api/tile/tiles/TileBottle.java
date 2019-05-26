package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBottle extends Tile {

	private BottleType type;
	
	public TileBottle(BottleType t) {
		type = t;
		hitbox = new Hitbox(type.length,type.length);
		sprite = new Sprite("tiles/bottle");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum BottleType{
		BEACH("beach/bottle_beach",0.5),
		BEACH2("beach2/beach2",0.5),
		HOLY_WATER("holyWater/bottle_water_holy",1),
		MESSAGE("message/bottlemessage",0.5),
		RIVER("river/bottle_river",0.5);
		
		private String name;
		private double length;
		
		private BottleType(String pName,double length) {
			name = pName;
			this.length = length;
		}
	}
}
