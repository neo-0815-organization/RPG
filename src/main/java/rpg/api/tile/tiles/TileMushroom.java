package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileMushroom extends Tile {
	
	private MushroomType type;

	public TileMushroom(MushroomType t) {
		type = t;
		sprite = new Sprite("tiles/mushroom");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum MushroomType{
		BLUE("blue/mushroom_blue"),
		BROWN("brown/mushroom_brown"),
		GREEN("green/mushroom_green"),
		RED("red/mushroom_red"),
		RED_DETAIL("red/mushroom_red_detailed");
		
		private String name;
		
		private MushroomType(String pName) {
			name = pName;
		}
	}
	
}
