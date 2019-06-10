package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileLog.LogType;

public class TileLog extends TypedTile<LogType> {

	public TileLog(final LogType type) {
		super(type);

		setSprite("log", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum LogType implements TileType {
		LEFT_SIDE("log1"),
		NORMAL("log2"),
		NORMAL_2("log3");

		private final String name;

		private LogType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
