package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileShell extends Tile {
	
	private ShellType type;

	public TileShell(ShellType t) {
		type = t;
		hitbox = new Hitbox(type.width,type.height);
		sprite = new Sprite("tiles/shell");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum ShellType{
		SPIKE("spike/shell_spike",0.5,0.5),
		TYPE_1("normal/shell",1,1),
		TYPE_2("normal/shell2",0.5,0.5);
		
		private String name;
		private double width;
		private double height;
		
		private ShellType(String pName,double w,double h) {
			name = pName;
			width = w;
			height = h;
		}
	}
	
}
