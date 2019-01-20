package rpg.api.collision;

import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * This {@link Hitbox} subclass explicitly describing a circle.
 * 
 * @author Tim Ludwig, Neo Hornberger(, Erik Diers, Jan Unterhuber)
 */
public class CircleHitbox extends Hitbox {
	protected double radius;
	
	/**
	 * Constructs a circular {@link Hitbox} with the radius 'radius'.
	 * 
	 * @param offset
	 *            the position of this {@link Hitbox}, relative to the
	 *            {@link Entity} or {@link Tile} represented by this
	 *            {@link Hitbox}
	 * @param radius
	 *            the radius of this {@link CircleHitbox}
	 */
	public CircleHitbox(final UnmodifiableVec2D offset, final double radius) {
		super(offset);
		
		this.radius = radius;
	}

	@Override
	public boolean checkCollision(final Hitbox colliderHitbox, final UnmodifiableVec2D colliderPosition) {
		if(colliderHitbox instanceof CircleHitbox) {
			final double r1 = radius, r2 = ((CircleHitbox) colliderHitbox).radius;
			
			return r1 * r1 + 2 * r1 * r2 + r2 * r2 > colliderPosition.add(getOffset(0)).subtract(colliderHitbox.getOffset(0)).magnitudeSquared();
		}else {
			final ModifiableVec2D collPos = colliderPosition.toModifiable();
			
			for(int i = 0; i < colliderHitbox.offsets.length; i++)
				if(checkCollision(colliderHitbox.getPoint(i).subtract(collPos))) return true;
			
			return false;
		}
	}
	
	@Override
	public boolean checkCollision(final Vec2D<?> colliderPoint) {
		return colliderPoint.subtract(getPosition()).magnitudeSquared() < radius * radius;
	}
}
