package rpg.api.entity;

import java.awt.image.BufferedImage;
import java.util.UUID;

import rpg.api.Direction;
import rpg.api.Location;
import rpg.api.Vec2D;
import rpg.api.localization.StringLocalizer;

/**
 * The abstract Class Entity.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer, Tim Ludwig
 */
public abstract class Entity {
	protected Location location;
	protected Direction lookingDirection;
	protected Vec2D velocity;
	protected BufferedImage image;
	protected String displayName;
	protected UUID uuid;
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param direction
	 *            the direction to accelerate in
	 * @param force
	 *            the accelerating amount of force
	 * @see #accelerate(Vec2D)
	 */
	public void accelerate(Direction direction, double force) {
		accelerate(direction.getVector().scale(force));
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param acc
	 *            the accelerating force
	 */
	public void accelerate(Vec2D acc) {
		velocity = velocity.add(acc);
	}
	
	/**
	 * Gets the unlocalized display name.
	 *
	 * @return the unlocalized display name
	 */
	public String getUnlocalizedDisplayName() {
		return displayName;
	}
	
	/**
	 * Gets the localized display name.
	 *
	 * @return the localized display name
	 */
	public String getDisplayName() {
		return StringLocalizer.localize(displayName);
	}
	
	/**
	 * Sets the display name.
	 *
	 * @param displayName
	 *            the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Gets the {@link Location}.
	 *
	 * @return the {@link Location}
	 */
	public Location getLocation() {
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
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public UUID getUniqueId() {
		return uuid;
	}
}
