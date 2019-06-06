package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.gfx.SpriteTheme;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileOre.OreType;

public class TileOre extends TypedTile<OreType> {

	public TileOre(final OreType type) {
		super(type);

		setSprite("ore", SpriteTheme.MOERSBERGWERKE, type.name);
	}

	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {
	}

	public enum OreType implements TileType {
		BLUE("ore_blue"),
		CRYSTAL_BLUE("ore_crystal_blue"),
		CRYSTAL_RED("ore_crystal_red"),
		EMPTY("ore_empty"),
		GOLD("ore_gold"),
		GREEN("ore_green"),
		GREY("ore_grey"),
		RED("ore_red"),
		SILVER("ore_silver");

		private final String name;

		private OreType(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
