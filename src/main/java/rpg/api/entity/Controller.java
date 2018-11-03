package rpg.api.entity;

import java.util.UUID;

import rpg.api.Direction;
import rpg.api.vector.ModifiableVec2D;

public interface Controller {
	
	/**
	 * Sets the display name of the {@link Entity} corresponding to this
	 * {@link Controller}
	 * 
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName);
	
	/**
	 * Sets the location {@link ModifiableVec2D} of the {@link Entity}
	 * corresponding to this {@link Controller}
	 * 
	 * @param location
	 *            the Location to set
	 */
	public void setLocation(ModifiableVec2D location);
	
	/**
	 * Sets the looking direction of the {@link Entity} corresponding to this
	 * {@link Controller}
	 * 
	 * @param direction
	 *            the {@link Direction} to set
	 */
	public void setLookingDirection(Direction direction);
	
	/**
	 * Sets the velocity {@link ModifiableVec2D} of the {@link Entity}
	 * corresponding to this {@link Controller}
	 * 
	 * @param velocity
	 *            the velocity {@link ModifiableVec2D} to set
	 */
	public void setVelocity(ModifiableVec2D velocity);
	
	/**
	 * Sets the {@link UUID} of the {@link Entity} corresponding to this
	 * {@link Controller}
	 * 
	 * @param uuid
	 *            the {@link UUID} to set
	 */
	public void setUniqueId(UUID uuid);
}
