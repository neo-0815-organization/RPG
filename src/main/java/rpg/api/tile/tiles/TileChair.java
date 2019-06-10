package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileChair.ChairType;

public class TileChair extends TypedTile<ChairType> {
	
	public TileChair(final ChairType type) {
		super(type);
		
		setSprite("chair", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum ChairType implements TileType {
		FRONT("front"),
		LEFT("left"),
		RIGHT("right"),
		BACK("back");
		
		private final String name;
		
		private ChairType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
