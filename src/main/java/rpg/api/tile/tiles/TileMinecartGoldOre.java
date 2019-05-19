package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileMinecartGoldOre extends Tile {
	
	public TileMinecartGoldOre() {
		sprite = new Sprite("tiles/minecart_gold_ore");
		sprite.addAnimation("minecart_gold_ore");
		sprite.setAnimation("minecart_gold_ore");
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
		
	}
	
}
