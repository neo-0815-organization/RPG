package rpg.api.tile;

import java.awt.Graphics2D;

import rpg.api.collision.Hitbox;
import rpg.api.entity.Entity;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.vector.ModifiableVec2D;

/**
 * The abstract class Tile represents everything a player can interact with
 * which is not an {@link Entity}.
 *
 * @author Tim Ludwig, Erik Diers
 */
public abstract class Tile implements ISprite {
	protected ModifiableVec2D	location;
	protected Sprite			sprite;
	protected Hitbox			hbox;
	
	/**
	 * Gets the location of this tile {@link ModifiableVec2D}
	 * 
	 * @return the location of this tile
	 */
	public ModifiableVec2D getLocation() {
		return location;
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, location);
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Gets the {@link Hitbox} of this {@link Tile}.
	 *
	 * @return the {@link Hitbox} of this {@link Tile}
	 */
	public Hitbox getHitbox() {
		return hbox;
	}
}
