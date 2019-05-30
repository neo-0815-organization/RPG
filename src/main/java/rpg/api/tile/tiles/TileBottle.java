package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileBottle extends Tile {
	private final BottleType type;
	
	public TileBottle(final BottleType type) {
		this.type = type;
		
		hitbox = new Hitbox(type.length, type.length);
		sprite = new Sprite("tiles/bottle");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum BottleType {
		BEACH("beach/bottle_beach", 0.5),
		BEACH2("beach2/beach2", 0.5),
		HOLY_WATER("holy_water/bottle_water_holy", 1),
		MESSAGE("message/bottle_message", 0.5),
		RIVER("river/bottle_river", 0.5);
		
		private final String name;
		private final double length;
		
		private BottleType(final String name, final double length) {
			this.name = name;
			this.length = length;
		}
	}
}
