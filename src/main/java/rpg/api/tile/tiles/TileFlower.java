package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileFlower extends Tile {
	
	public FlowerType type;

	public TileFlower(FlowerType t) {
		type = t;
		hitbox = new Hitbox(type.width,type.height);
		sprite = new Sprite("tiles/flower");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum FlowerType{
		CYAN("cyan/flower_cyan",0.5,0.5),
		PINK("pink/flower_pink",0.5,0.5),
		PURPLE_RIGHT("purple/right/flower_purple1",0.5,0.5),
		PURPLE_LEFT("purple/left/flower_purple2",0.5,0.5),
		RED_RIGHT("red/right/flower_red1",0.5,0.5),
		RED_LEFT("red/left/flower_red2",0.5,0.5),
		RED_MULTIPLE("red/multiple/flowe_red",1,1),
		YELLOW_RIGHT_LOW("yellow/rightLow/flower_yellow1",0.5,0.5),
		YELLOW_RIGHT("yellow/right/flower_yellow4",0.5,0.5),
		YELLOW_LEFT("yellow/left/flower_yellow3",0.5,0.5),
		YELLOW_LEFT_LOW("yellow/leftLow/flower_yellow2",0.5,0.5),
		YELLOW_MULTIPLE("yellow/multiple/flower_yellow",2,2);
		
		private String name;
		private double width;
		private double height;
		
		private FlowerType(String pName,double w,double h) {
			name = pName;
			width = w;
			height = h;
		}
	}
	
}
