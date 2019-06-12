package rpg.api.tile.tiles;

import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileFlower.FlowerType;

public class TileFlower extends TypedTile<FlowerType> {
	
	public TileFlower(final FlowerType type) {
		super(type);
		
		setSprite("flower", type.name);
	}
	
	public enum FlowerType implements TileType {
		CYAN("flower_cyan"),
		PINK("flower_pink"),
		PURPLE_RIGHT("flower_purple_right"),
		PURPLE_LEFT("flower_purple_left"),
		RED_RIGHT("flower_red_right"),
		RED_LEFT("flower_red_left"),
		RED_MULTIPLE("flower_red_multiple"),
		YELLOW_RIGHT_LOW("flower_yellow_right_low"),
		YELLOW_RIGHT("flower_yellow_right"),
		YELLOW_LEFT("flower_yellow_left"),
		YELLOW_LEFT_LOW("flower_yellow_left_low"),
		YELLOW_MULTIPLE("flower_yellow_multiple");
		
		private final String name;
		
		private FlowerType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
