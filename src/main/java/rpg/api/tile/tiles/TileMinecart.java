package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.Tile;

public class TileMinecart extends Tile {
	
	public MinecartType type;

	public TileMinecart(MinecartType t) {
		type = t;
		hitbox = new Hitbox(4,2);
		sprite = new Sprite("tiles/" + type.name, SpriteTheme.MOERSBERGWERKE);
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum MinecartType{
		EMPTY("minecart_empty"),
		GOLD_ORE("minecart_gold_ore");
		
		private String name;
		
		private MinecartType(String pName) {
			name = pName;
		}
	}
	
}
