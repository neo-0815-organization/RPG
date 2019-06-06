package rpg.api.entity;

import java.util.UUID;

import rpg.RPG;
import rpg.api.Direction;
import rpg.api.collision.Hitbox;
import rpg.api.collision.ICollideable;
import rpg.api.eventhandling.EventTrigger;
import rpg.api.eventhandling.EventType;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ISprite;
import rpg.api.gfx.Sprite;
import rpg.api.localization.INameable;
import rpg.api.tile.tiles.TileTent;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The abstract Class Entity.
 *
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer, Tim Ludwig
 */
public abstract class Entity implements INameable, ISprite, ICollideable, EventTrigger {
	protected ModifiableVec2D location;
	protected Sprite sprite;
	protected Direction lookingDirection = Direction.SOUTH;
	protected ModifiableVec2D velocity = ModifiableVec2D.ORIGIN.toModifiable();
	protected String displayName, imageName;
	protected UUID uuid;
	protected Hitbox hitbox;
	
	/**
	 * Constructs a new {@link Entity} with the display name 'name'.
	 * 
	 * @param name
	 *            the display name to set
	 */
	public Entity(final String name) {
		setDisplayName(name);
		
		uuid = UUID.randomUUID();
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param direction
	 *            the {@link Direction} to accelerate in
	 * @param force
	 *            the amount of accelerating force
	 * @see #accelerate(Vec2D)
	 */
	public void accelerate(final Direction direction, final double force) {
		accelerate(direction.getVector().scale(force));
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param acc
	 *            the accelerating force {@link Vec2D}
	 */
	public void accelerate(final Vec2D<?> acc) {
		velocity.add(acc);
	}
	
	/**
	 * Gets the {@link Vec2D} representing the location of this {@link Entity}.
	 *
	 * @return the {@link Vec2D} representing the location of this
	 *         {@link Entity}
	 */
	public ModifiableVec2D getLocation() {
		return location.toModifiable();
	}
	
	/**
	 * Sets the {@link Vec2D} representing the location of this {@link Entity}.
	 *
	 * @param the
	 *            {@link Vec2D} representing the location of this {@link Entity}
	 */
	public void setLocation(final Vec2D<?> location) {
		this.location = location.toModifiable();
	}
	
	/**
	 * Gets the looking {@link Direction} of this {@link Entity}.
	 *
	 * @return the looking {@link Direction} of this {@link Entity}
	 */
	public Direction getLookingDirection() {
		return lookingDirection;
	}
	
	/**
	 * Sets the looking {@link Direction} of this {@link Entity}.
	 *
	 * @param the
	 *            looking {@link Direction} of this {@link Entity}
	 */
	public void setLookingDirection(final Direction lookingDirection) {
		this.lookingDirection = lookingDirection;
	}
	
	/**
	 * Gets the looking {@link Vec2D} of this {@link Entity}.
	 *
	 * @return the looking {@link Vec2D} of this {@link Entity}
	 */
	public ModifiableVec2D getLookingVector() {
		return lookingDirection.getVector();
	}
	
	/**
	 * Gets the velocity {@link Vec2D} of this {@link Entity}.
	 *
	 * @return the velocity {@link Vec2D} of this {@link Entity}
	 */
	public ModifiableVec2D getVelocity() {
		return velocity;
	}
	
	/**
	 * Sets the velocity {@link Vec2D} of this {@link Entity}.
	 *
	 * @param the
	 *            velocity {@link Vec2D} of this {@link Entity}
	 */
	public void setVelocity(final ModifiableVec2D velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Gets the {@link UUID} of this {@link Entity}.
	 *
	 * @return the {@link UUID} of this {@link Entity}
	 */
	public UUID getUniqueId() {
		return uuid;
	}
	
	/**
	 * Sets the {@link UUID} of this {@link Entity}.
	 *
	 * @param the
	 *            {@link UUID} of this {@link Entity}
	 */
	public void setUniqueId(final UUID uuid) {
		this.uuid = uuid;
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
	
	/**
	 * Gets the {@link String} of this {@link Entity}.
	 *
	 * @return the {@link String} of this {@link Entity}
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 * Sets the {@link String} of this {@link Entity}.
	 *
	 * @param the
	 *            {@link String} of this {@link Entity}
	 */
	public void setImageName(final String imageName) {
		this.imageName = imageName;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Updates this {@link Entity}.
	 * 
	 * @param deltaTime
	 *            time since last frame in sec
	 */
	public void update(final double deltaTime) {
		location.add(velocity.toUnmodifiable().scale(deltaTime));
		
		sprite.update(deltaTime);
		
		//TODO reset
		RPG.gameField.checkCollisionTiles(this).forEach(t -> {
			if(t instanceof TileTent) t.triggerEvent(EventType.COLLISION_EVENT,t ,this); 
			else getHitbox().triggerEvent(EventType.COLLISION_EVENT, this, t);
		});
		RPG.gameField.checkCollisionEntities(this).forEach(e -> triggerEvent(EventType.COLLISION_EVENT, this, e));
	}
	
	/**
	 * Sets the {@link Sprite} of this {@link Entity}.
	 *
	 * @param the
	 *            {@link Sprite} of this {@link Entity}
	 */
	public void setSprite(final Sprite sprite) {
		this.sprite = sprite;
	}
	
	@Override
	public void draw(final DrawingGraphics g) {
		draw(g, location);
	}
	
	/**
	 * Returns a human readable representation of this {@link Entity} looking
	 * like Entity@hash[{@link UUID}, displayName].
	 *
	 * @return the textual representation of this {@link Entity}
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[uuid=" + uuid + ", displayName=" + displayName + "]";
	}
}
