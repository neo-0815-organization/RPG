package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileWagon.WagonType;

public class TileWagon extends TypedTile<WagonType> {

	public TileWagon(final WagonType type) {
		super(type);

		setSprite("wagon", SpriteTheme.MOERSBERGE, type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum WagonType implements TileType {
		NORMAL("wagon"),
		DAMAGED("wagon_damaged");

		private final String name;

		private WagonType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
