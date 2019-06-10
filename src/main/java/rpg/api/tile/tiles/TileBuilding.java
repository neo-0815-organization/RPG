package rpg.api.tile.tiles;

import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileBuilding.BuildingType;

public class TileBuilding extends TypedTile<BuildingType> {
	
	public TileBuilding(final BuildingType type) {
		super(type);
		
		setSprite(type.sprite, type.theme, type.name);
	}
	
	public enum BuildingType implements TileType {
		NORMAL_HOUSE("house", SpriteTheme.NONE),
		FAIRY_HOUSE("house_fairy", SpriteTheme.SCHALLENBERGE),
		DWARF_BARRACKS("barracks", "barrack_dwarf", SpriteTheme.DWARF_CITY),
		DWARF_HOUSE("house_dwarf", SpriteTheme.DWARF_CITY),
		DWARF_HOUSE_2("house_dwarf_2", SpriteTheme.DWARF_CITY);
		
		private final String sprite, name;
		private final SpriteTheme theme;
		
		private BuildingType(final String sprite, final String name, final SpriteTheme theme) {
			this.sprite = sprite;
			this.name = name;
			this.theme = theme;
		}
		
		private BuildingType(final String name, final SpriteTheme theme) {
			this("house", name, theme);
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
