package rpg.api.tile;

import rpg.api.eventhandling.EventType;

public abstract class TypedTile<T extends TileType> extends Tile {
	protected final T type;
	
	public TypedTile(final T type) {
		this.type = type;
	}
	
	@Override
	public void triggerEvent(final EventType eventType, final Object... objects) {}
}
