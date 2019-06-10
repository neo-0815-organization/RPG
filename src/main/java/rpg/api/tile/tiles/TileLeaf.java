package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileLeaf.LeafType;

public class TileLeaf extends TypedTile<LeafType> {

	public TileLeaf(final LeafType type) {
		super(type);

		setSprite("leaf", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum LeafType implements TileType {
		GREEN("leaf_green"),
		ORANGE("leaf_orange");

		private final String name;

		private LeafType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
