package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileBuilding extends Tile {
	
	private BuildingType type;

	public TileBuilding(BuildingType t) {
		type = t;
		sprite = new Sprite("tiles",type.theme);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum BuildingType{
		NORMAL_HOUSE("house/house",SpriteTheme.NONE),
		FAIRY_HOUSE("house/house_fee",SpriteTheme.SCHALLENBERGE),
		DWARF_BARRACKS("barracks/barracks_dwarf",SpriteTheme.DWARF_CITY),
		DWARF_HOUSE("house/house_dwarf",SpriteTheme.DWARF_CITY),
		DWARF_HOUSE_2("house(house_dwarf_2",SpriteTheme.DWARF_CITY);
		
		private String name;
		private SpriteTheme theme;
		
		private BuildingType(String pName, SpriteTheme pTheme) {
			name = pName;
			theme = pTheme;
		}
		
	}
	
}
