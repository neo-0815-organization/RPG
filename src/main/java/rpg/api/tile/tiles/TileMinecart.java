package rpg.api.tile.tiles;

import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileMinecart.MinecartType;

public class TileMinecart extends TypedTile<MinecartType> {
	
	public TileMinecart(final MinecartType type) {
		super(type);
		
		setSprite("minecart", SpriteTheme.MOERSBERGWERKE, type.name);
	}
	
	public enum MinecartType implements TileType {
		EMPTY("minecart_empty"),
		GOLD_ORE("minecart_gold_ore");
		
		private final String name;
		
		private MinecartType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
