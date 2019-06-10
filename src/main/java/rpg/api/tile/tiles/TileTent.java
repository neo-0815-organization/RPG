package rpg.api.tile.tiles;

import java.util.LinkedList;

import rpg.RPG;
import rpg.api.eventhandling.EventType;
import rpg.api.scene.Background;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTent.TentType;

public class TileTent extends TypedTile<TentType> {

	public TileTent(final TentType type) {
		super(type);

		setSprite("tent", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
  
	public enum TentType implements TileTypeSized {
		NORMAL("tent_normal", 3, 2),
		RED("tent_red", 2, 2),
		YELLOW("tent_yellow", 2, 2);

		private final String	name;
		private final double	width, height;

		private TentType(final String name, final double width, final double height) {
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
