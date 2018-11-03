package rpg.api.entity;

import java.awt.Graphics2D;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.INameable;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The abstract Class Entity.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer, Tim Ludwig
 */
public abstract class Entity implements INameable, ISprite {
	protected ModifiableVec2D location;
	protected Sprite sprite;
	protected Direction lookingDirection;
	protected ModifiableVec2D velocity;
	protected String displayName, imageName;
	protected UUID uuid;
	
	public Entity(final String name) {
		setDisplayName(name);
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param direction
	 *            the {@link Direction} to accelerate in
	 * @param force
	 *            the amount of accelerating force
	 * @see #accelerate(UnmodifiableVec2D)
	 */
	public void accelerate(final Direction direction, final double force) {
		accelerate(direction.getVector().scale(force));
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param acc
	 *            the accelerating force {@link UnmodifiableVec2D}
	 * @see #accelerate(Direction, double)
	 */
	public void accelerate(final Vec2D<?> acc) {
		velocity.add(acc);
	}
	
	/**
	 * Gets the {@link ModifiableVec2D} representing the location of this
	 * entity.
	 *
	 * @return the {@link ModifiableVec2D} representing the location of this
	 *         entity.
	 */
	public ModifiableVec2D getLocation() {
		return location.toModifiable();
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
	 * Gets the looking {@link UnmodifiableVec2D}.
	 *
	 * @return the looking {@link UnmodifiableVec2D}
	 */
	public ModifiableVec2D getLookingVector() {
		return lookingDirection.getVector();
	}
	
	/**
	 * Gets the velocity {@link UnmodifiableVec2D}.
	 *
	 * @return the velocity {@link UnmodifiableVec2D}
	 */
	public ModifiableVec2D getVelocity() {
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
	 * Returns a human readable representation of this {@link Entity} looking
	 * like Entity@hash[x, y]
	 * 
	 * @return the textual representation of this {@link Entity}
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[uuid=" + uuid + ", displayName=" + displayName + "]";
	}
}
