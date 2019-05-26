package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileLeaf extends Tile {
	
	public LeafType type;

	public TileLeaf(LeafType pType) {
		type = pType;
		sprite = new Sprite("tiles/leaf");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	
	public enum LeafType{
		GREEN("green/leaf_green"),
		ORANGE("orange/leaf_orange");
		
		private String name;
		
		private LeafType(String pName) {
			name = pName;
		}
	}
}
