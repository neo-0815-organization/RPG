package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileBottle.BottleType;

public class TileBottle extends TypedTile<BottleType> {

	public TileBottle(final BottleType type) {
		super(type);

		setSprite("bottle", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum BottleType implements TileTypeSized {
		BEACH("beach/bottle_beach", 0.5),
		BEACH2("beach2/beach2", 0.5),
		HOLY_WATER("holy_water/bottle_water_holy", 1),
		MESSAGE("message/bottle_message", 0.5),
		RIVER("river/bottle_river", 0.5);

		private final String	name;
		private final double	size;

		private BottleType(final String name, final double size) {
			this.name = name;
			this.size = size;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public double getWidth() {
			return size;
		}

		@Override
		public double getHeight() {
			return size;
		}
	}
}
