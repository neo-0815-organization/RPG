package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileMinecart.MinecartType;

public class TileMinecart extends TypedTile<MinecartType> {
	
	public TileMinecart(final MinecartType type) {
		super(type);
		
		setHitbox(4, 2);
		setSprite("minecart", SpriteTheme.MOERSBERGWERKE, type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
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
