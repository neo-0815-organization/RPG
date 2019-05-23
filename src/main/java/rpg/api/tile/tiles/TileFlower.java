package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileFlower extends Tile {
	
	public FlowerType type;

	public TileFlower(FlowerType t) {
		type = t;
		sprite = new Sprite("tiles/flower");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum FlowerType{
		CYAN("cyan/flower_cyan"),
		PINK("pink/flower_pink"),
		PURPLE_RIGHT("purple/right/flower_purple1"),
		PURPLE_LEFT("purple/left/flower_purple2"),
		RED_RIGHT("red/right/flower_red1"),
		RED_LEFT("red/left/flower_red2"),
		RED_MULTIPLE("red/multiple/flowe_red"),
		YELLOW_RIGHT_LOW("yellow/rightLow/flower_yellow1"),
		YELLOW_RIGHT("yellow/right/flower_yellow4"),
		YELLOW_LEFT("yellow/left/flower_yellow3"),
		YELLOW_LEFT_LOW("yellow/leftLow/flower_yellow2"),
		YELLOW_MULTIPLE("yellow/multiple/flower_yellow");
		
		private String name;
		
		private FlowerType(String pName) {
			name = pName;
		}
	}
	
}
