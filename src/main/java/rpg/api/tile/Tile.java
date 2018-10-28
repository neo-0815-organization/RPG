package rpg.api.tile;

import java.awt.Graphics2D;

import rpg.api.Vec2D;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;

/**
 * The abstract class Tile represents everything a player can interact with
 * which is not an {@link Entity}.
 *
 * @author Tim Ludwig, Erik Diers
 */
public abstract class Tile implements ISprite {
	protected Vec2D		locaction;
	protected Sprite	sprite;
	protected Hitbox	hbox;
	
	/**
	 * Gets the location {@link Vec2D} of this {@link Tile}.
	 *
	 * @return the location of this {@link Tile}
	 */
	public Vec2D getLocation() {
		return locaction;
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, locaction);
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
