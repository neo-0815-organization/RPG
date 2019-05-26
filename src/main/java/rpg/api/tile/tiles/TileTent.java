package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTent extends Tile {
	
	public TentType type;
	
	public TileTent(TentType t) {
		type = t;
		hitbox = new Hitbox(type.width,type.height);
		sprite = new Sprite("tiles/tent");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}
	
	
	public enum TentType{
		NORMAL("tent",3,2),
		RED("tent_red",2,2),
		YELLOW("tent_blue",2,2);
		
		private String name;
		private double width;
		private double height;
		
		
		private TentType(String pName, double w, double h) {
			name = pName;
			width = w;
			height = h;
		}
	}
}
