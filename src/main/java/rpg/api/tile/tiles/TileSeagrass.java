package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileSeagrass.SeagrassType;

public class TileSeagrass extends TypedTile<SeagrassType> {

	public TileSeagrass(final SeagrassType type) {
		super(type);

		setSprite("seagrass", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum SeagrassType implements TileType {
		NORMAL("seagrass"),
		TYPE_2("seagrass2");

		private final String name;

		private SeagrassType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
