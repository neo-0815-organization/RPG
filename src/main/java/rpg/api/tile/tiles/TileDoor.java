package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileDoor extends Tile {
	private DoorType type;

	public TileDoor(DoorType t) {
		type = t;
		
		hitbox = new Hitbox(type.witdh, type.height);
		sprite = new Sprite("tiles/" + type.name);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}

	public enum DoorType{
		NORMAL("door", 2, 1.5),
		TATTICE("door_tattice", 1, 1.5);
		
		private String name;
		private double witdh;
		private double height;
		
		private DoorType(String pName, double w, double h) {
			name = pName;
			witdh = w;
			height = h;
		}
	}
}
