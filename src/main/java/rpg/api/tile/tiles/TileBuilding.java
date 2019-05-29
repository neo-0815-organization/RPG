package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileBuilding extends Tile {
	
	private BuildingType type;

	public TileBuilding(BuildingType t) {
		type = t;
		hitbox = new Hitbox(type.length,type.height);
		sprite = new Sprite("tiles",type.theme);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum BuildingType{
		NORMAL_HOUSE("house/house",SpriteTheme.NONE,8,4),
		FAIRY_HOUSE("house/house_fee",SpriteTheme.SCHALLENBERGE,3,3),
		DWARF_BARRACKS("barracks/barracks_dwarf",SpriteTheme.DWARF_CITY,9,5),
		DWARF_HOUSE("house/house_dwarf",SpriteTheme.DWARF_CITY,8,5),
		DWARF_HOUSE_2("house(house_dwarf_2",SpriteTheme.DWARF_CITY,7,8);
		
		private String name;
		private SpriteTheme theme;
		private double length;
		private double height;
		
		private BuildingType(String pName, SpriteTheme pTheme,double l, double w) {
			name = pName;
			theme = pTheme;
			length = l;
			height = w;
		}
		
	}
	
}
