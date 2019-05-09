package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileOre extends Tile{
	private OreType type;
	
	public TileOre(OreType t){
		sprite = new Sprite("tiles/ore", SpriteTheme.MOERSBERGWERKE);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {}

	public enum OreType{
		BLUE("ore_blue"),
		CRYSTAL_BLUE("ore_crystal_blue"),
		CRYSTAL_RED("ore_crystal_red"),
		EMPTY("ore_empty"),
		GOLD("ore_gold"), 
		GREEN("ore_green"),
		GREY("ore_grey"),
		RED("ore_red"),
		SILVER("ore_silver");
		
		private String name;
		
		private OreType(String name) {
			this.name = name;
		}
	}
}
