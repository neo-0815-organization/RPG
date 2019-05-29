package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.vector.Vec2D;

/**
 * The interface controller used to control an {@link Entity}.
 *
 * @author Neo Hornberger, Tim Ludwig
 */
public interface Controller {
	/**
	 * Gets the {@link Entity} corresponding to this {@link Controller}.
	 *
	 * @return the {@link Entity} corresponding to this {@link Controller}
	 */
	Entity getEntity();
	
	/**
	 * Sets the display name of the {@link Entity} corresponding to this
	 * {@link Controller}.
	 *
	 * @param displayName
	 *            the displayName to set
	 */
	default void setDisplayName(final String displayName) {
		getEntity().displayName = displayName;
	}
	
	/**
	 * Sets the location {@link Vec2D} of the {@link Entity} corresponding to
	 * this {@link Controller}.
	 *
	 * @param location
	 *            the location {@link Vec2D} to set
	 */
	default void setLocation(final Vec2D<?> location) {
		getEntity().location = location.toModifiable();
	}
	
	/**
	 * Sets the looking {@link Direction} of the {@link Entity} corresponding to
	 * this {@link Controller}.
	 *
	 * @param direction
	 *            the {@link Direction} to set
	 */
	default void setLookingDirection(final Direction direction) {
		getEntity().lookingDirection = direction;
	}
	
	/**
	 * Sets the velocity {@link Vec2D} of the {@link Entity} corresponding to
	 * this {@link Controller}.
	 *
	 * @param velocity
	 *            the velocity {@link Vec2D} to set
	 */
	default void setVelocity(final Vec2D<?> velocity) {
		getEntity().velocity = velocity.toModifiable();
	}
	
	/**
	 * Sets the {@link UUID} of the {@link Entity} corresponding to this
	 * {@link Controller}.
	 *
	 * @param uuid
	 *            the {@link UUID} to set
	 */
	default void setUniqueId(final UUID uuid) {
		getEntity().uuid = uuid;
	}
}
