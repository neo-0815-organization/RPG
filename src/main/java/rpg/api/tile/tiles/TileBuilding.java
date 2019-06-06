package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileBuilding.BuildingType;

public class TileBuilding extends TypedTile<BuildingType> {

	public TileBuilding(final BuildingType type) {
		super(type);

		setSprite("", type.theme, type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum BuildingType implements TileTypeSized {
		NORMAL_HOUSE("house/house", SpriteTheme.NONE, 8, 4),
		FAIRY_HOUSE("house/house_fairy", SpriteTheme.SCHALLENBERGE, 3, 3),
		DWARF_BARRACKS("barracks/barracks_dwarf", SpriteTheme.DWARF_CITY, 9, 5),
		DWARF_HOUSE("house/house_dwarf", SpriteTheme.DWARF_CITY, 8, 5),
		DWARF_HOUSE_2("house/house_dwarf_2", SpriteTheme.DWARF_CITY, 7, 8);

		private final String		name;
		private final SpriteTheme	theme;
		private final double		width, height;

		private BuildingType(final String name, final SpriteTheme theme, final double width, final double height) {
			this.name = name;
			this.theme = theme;
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
