package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileCandle.CandleType;

public class TileCandle extends TypedTile<CandleType> {
	
	public TileCandle(final CandleType type) {
		super(type);
		
		setSprite("candle", type.name);
	}
	
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
