package rpg.api.collision;

import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

public class CircleHitbox extends Hitbox{
	protected double radius;
	
	public CircleHitbox(UnmodifiableVec2D offset, double radius) {
		super(1, offset);
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
	public boolean checkCollision(Vec2D<?> colliderPoint) {
		return colliderPoint.subtract(getOffset()).magnitudeSquared() < radius * radius;
	}
	
	private UnmodifiableVec2D getOffset() {
		return getOffset(0);
	}
}
