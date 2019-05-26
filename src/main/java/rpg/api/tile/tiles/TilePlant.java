package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.Sprite;
import rpg.api.tile.Tile;

public class TilePlant extends Tile {
	
	private PlantType type;

	public TilePlant(PlantType t) {
		type = t;
		sprite = new Sprite("tiles/plant");
		sprite.addAnimation(type.name);
		sprite.setAnimation(type.name);
	}

	@Override
	public void triggerEvent(EventType eventType, Object... objects) {
		// TODO Auto-generated method stub

	}

	public enum PlantType{
		NORMAL("plant"),
		SOYBEAN("soybean/plant_soybeans");
		
		private String name;
		
		private PlantType(String pName) {
			name = pName;
		}
	}
	
}
