package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileDoor.DoorType;

public class TileDoor extends TypedTile<DoorType> {

	public TileDoor(final DoorType type) {
		super(type);

		setSprite("door", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum DoorType implements TileTypeSized {
		NORMAL("door", 2, 1.5),
		TATTICE("door_tattice", 1, 1.5);

		private final String	name;
		private final double	width, height;

		private DoorType(final String name, final double width, final double height) {
			this.name = name;
			this.width = width;
			this.height = height;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public double getWidth() {
			return width;
		}

		@Override
		public double getHeight() {
			return height;
		}
	}
}
