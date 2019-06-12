package rpg.api.tile.tiles;

import rpg.api.collision.Hitbox;
import rpg.api.tile.Tile;

public class TileBarrier extends Tile {
	
	public TileBarrier() {
		setHitbox(1);
	}
	
	public TileBarrier(final Hitbox hitbox) {
		this.hitbox = hitbox;
	}
}
