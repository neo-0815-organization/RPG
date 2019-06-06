package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTree.TreeType;

public class TileTree extends TypedTile<TreeType> {

	public TileTree(final TreeType type) {
		super(type);

		setSprite("tree", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum TreeType implements TileTypeSized {
		NORMAL("tree", 4),
		APPLE("tree_apple", 4),
		APPLE_2("tree_apple2", 4),
		BIG_FACE("tree_big_face", 4),
		BIG("tree_big", 4),
		DEAD("tree_dead", 4),
		DEAD_2("tree_dead_2", 4),
		FIRE_SNOW("tree_fire_snow", 2),
		FIRE("tree_fire", 2),
		PALM("tree_palm", 2, 3);

		private final String	name;
		private final double	width, height;

		private TreeType(final String name, final double size) {
			this(name, size, size);
		}

		private TreeType(final String name, final double width, final double height) {
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
