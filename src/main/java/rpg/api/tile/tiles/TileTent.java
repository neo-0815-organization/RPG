package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileTent extends Tile {
	
	public TentType type;
	
	public TileTent(final TentType type) {
		this.type = type;
		
		hitbox = new Hitbox(type.width, type.height);
		sprite = new Sprite("tiles/tent");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}
	
	public enum TentType{
		NORMAL("tent", 3, 2),
		RED("tent_red", 2, 2),
		YELLOW("tent_blue", 2, 2);
		
		private final String name;
		private final double width;
		private final double height;
		
		private TentType(final String name, final double width, final double height) {
			this.name = name;
			this.width = width;
			this.height = height;
		}
	}
}
