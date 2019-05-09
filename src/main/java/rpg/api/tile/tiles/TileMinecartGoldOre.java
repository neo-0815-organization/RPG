package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileMinecartGoldOre extends Tile {

	public TileMinecartGoldOre() {
		sprite = new Sprite("tiles/minecart_gold_ore", SpriteTheme.MOERSBERGWERKE);
		sprite.addAnimation("minecart_gold_ore");
		sprite.setAnimation("minecart_gold_ore");
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

}
