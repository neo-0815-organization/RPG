package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileTree extends Tile {
	
	public enum TreeType{
		NORMAL("tree"),
		APPLE("tree_apple"),
		APPLE_2("tree_apple2"),
		BIG_FACE("tree_big_face"),
		BIG("tree_big"),
		DEAD("tree_dead"),
		DEAD_2("tree_dead_2"),
		FIRE_SNOW("tree_fire_snow"),
		FIRE("tree_fire"),
		PALM("tree_palm");
		
		public String name;
		
		private TreeType(String pName) {
			name = pName;
		}
	}
	
	private TreeType type;

	public TileTree(TreeType t) {
		type = t;
		sprite = new Sprite("tiles/tree");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
		
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
