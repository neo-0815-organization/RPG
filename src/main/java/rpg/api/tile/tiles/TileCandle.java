package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileCandle.CandleType;

public class TileCandle extends TypedTile<CandleType> {
	
	public TileCandle(final CandleType type) {
		super(type);
		
		setHitbox(0.5, 1);
		setSprite("candle", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum CandleType implements TileType {
		BRONZE("bronze/candle_bronze"),
		GOLD("gold/candle_gold"),
		SILVER("silver/candle_silver");
		
		private final String name;
		
		private CandleType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
