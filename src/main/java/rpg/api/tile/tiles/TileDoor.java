package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileDoor.DoorType;

public class TileDoor extends TypedTile<DoorType> {
	
	public TileDoor(final DoorType type) {
		super(type);
		
		setSprite("door", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum DoorType implements TileType {
		NORMAL("door"),
		TATTICE("door_tattice");
		
		private final String name;
		
		private DoorType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
