package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileFlag.FlagType;

public class TileFlag extends TypedTile<FlagType> {

	public TileFlag(final FlagType type) {
		super(type);

		setSprite("flag", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum FlagType implements TileType {
		RED("flag_red"),
		BLUE("flag_blue"),
		YELLOW("flag_yellow");

		private final String name;

		private FlagType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
