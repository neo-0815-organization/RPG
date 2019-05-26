package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileTree extends Tile {
	
	public enum TreeType{
		NORMAL("tree",4,4),
		APPLE("tree_apple",4,4),
		APPLE_2("tree_apple2",4,4),
		BIG_FACE("tree_big_face",4,4),
		BIG("tree_big",4,4),
		DEAD("tree_dead",4,4),
		DEAD_2("tree_dead_2",4,4),
		FIRE_SNOW("tree_fire_snow",2,2),
		FIRE("tree_fire",2,2),
		PALM("tree_palm",2,3);
		
		private String name;
		private double width;
		private double height;
		
		private TreeType(String pName, double w, double h) {
			name = pName;
			width = w;
			height = h;
		}
	}
	
	private TreeType type;

	public TileTree(TreeType t) {
		type = t;
		hitbox = new Hitbox(type.width, type.height);
		sprite = new Sprite("tiles/tree");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
		
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
