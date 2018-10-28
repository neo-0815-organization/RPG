package rpg.api.entity;

import java.awt.Graphics2D;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.INameable;
import rpg.api.Vec2D;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;

/**
 * The abstract Class Entity.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer, Tim Ludwig
 */
public abstract class Entity implements INameable, ISprite {
	protected Vec2D		location;
	protected Sprite	sprite;
	protected Direction	lookingDirection;
	protected Vec2D		velocity;
	protected String	displayName,
			imageName;
	protected UUID		uuid;
	
	public Entity(final String name) {
		setDisplayName(name);
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param direction
	 *        the {@link Direction} to accelerate in
	 * @param force
	 *        the amount of accelerating force
	 * @see #accelerate(Vec2D)
	 */
	public void accelerate(final Direction direction, final double force) {
		accelerate(direction.getVector().scale(force));
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param acc
	 *        the accelerating force {@link Vec2D}
	 * @see #accelerate(Direction, double)
	 */
	public void accelerate(final Vec2D acc) {
		velocity = velocity.add(acc);
	}
	
	/**
	 * Gets the {@link Vec2D} representing the location of this entity.
	 *
	 * @return the {@link Vec2D} representing the location of this entity.
	 */
	public Vec2D getLocation() {
		return location;
	}
	
	/**
	 * Gets the looking {@link Direction}.
	 *
	 * @return the looking {@link Direction}
	 */
	public Direction getLookingDirection() {
		return lookingDirection;
	}
	
	/**
	 * Gets the looking {@link Vec2D}.
	 *
	 * @return the looking {@link Vec2D}
	 */
	public Vec2D getLookingVector() {
		return lookingDirection.getVector();
	}
	
	/**
	 * Gets the velocity {@link Vec2D}.
	 *
	 * @return the velocity {@link Vec2D}
	 */
	public Vec2D getVelocity() {
		return velocity;
	}
	
	/**
	 * Gets the {@link UUID}.
	 *
	 * @return the {@link UUID}
	 */
	public UUID getUniqueId() {
		return uuid;
	}
	
	@Override
	public String getUnlocalizedName() {
		return displayName;
	}
	
	@Override
	public void setDisplayName(String displayName) {
		if(!displayName.endsWith(".name")) displayName += ".name";
		
		this.displayName = displayName;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		draw(g2d, location);
	}
	
	/**
	 * Returns a human readable representation of this {@link Entity} looking like
	 * Entity@hash[x, y]
	 * 
	 * @return the textual representation of this {@link Entity}
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[uuid=" + uuid + ", displayName=" + displayName + "]";
	}
}
