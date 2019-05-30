package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileType;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileTreestump.TreestumpType;

public class TileTreestump extends TypedTile<TreestumpType> {
	
	public TileTreestump(final TreestumpType type) {
		super(type);
		
		setHitbox(3, 3);
		setSprite("treestump", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum TreestumpType implements TileType {
		NORMAL("treestump"),
		SNOWED("treestump_snowed");
		
		private final String name;
		
		private TreestumpType(final String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
