package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileOreCrystalRed extends Tile {
	
	public TileOreCrystalRed() {
		sprite = new Sprite("tiles/ore_crystal_red");
		sprite.addAnimation("ore_crystal_red");
		sprite.setAnimation("ore_crystal_red");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
