package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreCrystalBlue extends Tile {
	
	public TileOreCrystalBlue() {
		sprite = new Sprite("tiles/ore_crystal_blue");
		sprite.addAnimation("ore_crystal_blue");
		sprite.setAnimation("ore_crystal_blue");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
