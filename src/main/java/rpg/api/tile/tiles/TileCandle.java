package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TileCandle extends Tile {

	public CandleType type;
	
	public TileCandle(CandleType t) {
		 type  =  t;
		 sprite = new Sprite("tiles/candle");
		 sprite.addAnimation(type.name);
		 sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum CandleType{
		BRONZE("bronze/candle_bronze"),
		GOLD("gold/candle_gold"),
		SILVER("silver/candle_silver");
		
		public String name;
		
		private CandleType(String pName) {
			name = pName;
		}
	}
	
}
