package rpg.api.collision;

import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

public class CircleHitbox extends Hitbox{
	protected double radius;
	
	public CircleHitbox(UnmodifiableVec2D offset, double radius) {
		super(offset);
		this.radius = radius;
	}

	@Override
	public boolean checkCollision(Vec2D<?> colliderPoint) {
		return colliderPoint.subtract(getOffset()).magnitudeSquared() < radius * radius;
	}
	
	private UnmodifiableVec2D getOffset() {
		return getOffset(0);
	}
}
