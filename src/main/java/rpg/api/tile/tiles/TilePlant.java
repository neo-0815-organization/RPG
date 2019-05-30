package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TilePlant.PlantType;

public class TilePlant extends TypedTile<PlantType> {
	
	public TilePlant(final PlantType type) {
		super(type);
		
		setHitbox(1);
		setSprite("plant", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum PlantType implements TileType {
		NORMAL("plant"),
		SOYBEAN("plant_soybeans");
		
		private final String name;
		
		private PlantType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
