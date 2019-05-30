package rpg.api.tile.tiles;

import rpg.api.eventhandling.EventType;
import rpg.api.tile.TileTypeSized;
import rpg.api.tile.TypedTile;
import rpg.api.tile.tiles.TileShell.ShellType;

public class TileShell extends TypedTile<ShellType> {
	
	public TileShell(final ShellType type) {
		super(type);
		
		setHitbox(type.size);
		setSprite("shell", type.name);
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
	
	public enum ShellType implements TileTypeSized {
		SPIKE("shell_spike", 0.5),
		TYPE_1("shell", 1),
		TYPE_2("shell2", 0.5);
		
		private final String name;
		private final double size;
		
		private ShellType(final String name, final double size) {
			this.name = name;
			this.size = size;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public double getWidth() {
			return size;
		}
		
		@Override
		public double getHeight() {
			return size;
		}
	}
}
