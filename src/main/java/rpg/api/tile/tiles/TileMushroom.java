package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileMushroom.MushroomType;

public class TileMushroom extends TypedTile<MushroomType> {

	public TileMushroom(final MushroomType type) {
		super(type);

		setSprite("mushroom", type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum MushroomType implements TileType {
		BLUE("mushroom_blue"),
		BROWN("mushroom_brown"),
		GREEN("mushroom_green"),
		RED("mushroom_red"),
		RED_DETAIL("mushroom_red_detailed");

		private final String name;

		private MushroomType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
