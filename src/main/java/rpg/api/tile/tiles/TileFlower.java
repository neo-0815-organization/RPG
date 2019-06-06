package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileFlower.FlowerType;

public class TileFlower extends TypedTile<FlowerType> {

	public TileFlower(final FlowerType type) {
		super(type);

		setSprite("flower", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum FlowerType implements TileTypeSized {
		CYAN("flower_cyan", 0.5),
		PINK("flower_pink", 0.5),
		PURPLE_RIGHT("flower_purple_right", 0.5),
		PURPLE_LEFT("flower_purple_left", 0.5),
		RED_RIGHT("flower_red_right", 0.5),
		RED_LEFT("flower_red_left", 0.5),
		RED_MULTIPLE("flower_red_multiple", 1),
		YELLOW_RIGHT_LOW("flower_yellow_right_low", 0.5),
		YELLOW_RIGHT("flower_yellow_right", 0.5),
		YELLOW_LEFT("flower_yellow_left", 0.5),
		YELLOW_LEFT_LOW("flower_yellow_left_low", 0.5),
		YELLOW_MULTIPLE("flower_yellow_multiple", 2);

		private final String	name;
		private final double	size;

		private FlowerType(final String name, final double size) {
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
