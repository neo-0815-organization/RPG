package rpg.api.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.UUID;

import rpg.RPG;
import rpg.api.Direction;
import rpg.api.INameable;
import rpg.api.Location;
import rpg.api.Vec2D;
import rpg.api.gfx.IDrawable;
import rpg.api.gfx.Sprite;
import rpg.api.scene.Camera;

/**
 * The abstract Class Entity.
 * 
 * @author Neo Hornberger, Alexander Schallenberg, Vincent Grewer, Tim Ludwig
 */
public abstract class Entity implements INameable, IDrawable {
	protected Location	location;
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
	 *        the direction to accelerate in
	 * @param force
	 *        the accelerating amount of force
	 * @see #accelerate(Vec2D)
	 */
	public void accelerate(final Direction direction, final double force) {
		accelerate(direction.getVector().scale(force));
	}
	
	/**
	 * Accelerates this {@link Entity}.
	 *
	 * @param acc
	 *        the accelerating force
	 */
	public void accelerate(final Vec2D acc) {
		velocity = velocity.add(acc);
	}
	
	/**
	 * Gets the unlocalized name.
	 * 
	 * @see INameable#getUnlocalizedName()
	 * @return the unlocalized name
	 */
	@Override
	public String getUnlocalizedName() {
		return displayName;
	}
	
	/**
	 * Sets the display name.
	 * 
	 * @see INameable#setDisplayName(String)
	 * @param displayName
	 *        the new display name
	 */
	@Override
	public void setDisplayName(String displayName) {
		if(!displayName.endsWith(".name")) displayName += ".name";
		
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
	
	@Override
	public void draw(final Graphics2D g2d) {
		final Rectangle screen = new Rectangle(Camera.loc.getX(), Camera.loc.getY(), RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
		// TODO: implement animatin name selection
		if(screen.intersects(getCurrentImageBoundings())) g2d.drawImage(sprite.getNextAnimationFrame(""), location.getX() - Camera.loc.getX(), location.getY() - Camera.loc.getY(), null);
	}
	
	public Rectangle getCurrentImageBoundings() {
		return new Rectangle(location.getX(), location.getY(), 16, 16);
	}
	
	@Override
	public String toString() {
		return super.toString() + "[uuid=" + uuid + ", displayName=" + displayName + "]";
	}
}
