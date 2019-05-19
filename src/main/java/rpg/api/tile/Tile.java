package rpg.api.tile;

import java.awt.Color;

import rpg.api.collision.Hitbox;
import rpg.api.collision.ICollideable;
import rpg.api.entity.Entity;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;

/**
 * The abstract class Tile represents everything a player can interact with
 * which is not an {@link Entity}.
 *
 * @author Tim Ludwig, Erik Diers
 */
public abstract class Tile implements ISprite, ICollideable, EventTrigger {
	protected UnmodifiableVec2D location;
	protected Sprite sprite;
	protected Hitbox hitbox;
	
	/**
	 * Gets the location of this tile {@link ModifiableVec2D}
	 * 
	 * @return the location of this tile
	 */
	public UnmodifiableVec2D getLocation() {
		return location;
	}
	
	/**
	 * This update-method is used to update tiles, whenever it is needed.
	 * 
	 * @param deltaTime
	 *            time since last frame in sec.
	 */
	public void update(final double deltaTime) {}
	
	@Override
	public void draw(final DrawingGraphics g) {
		draw(g, getLocation());
		
		g.setColor(Color.BLACK);
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
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}
}
