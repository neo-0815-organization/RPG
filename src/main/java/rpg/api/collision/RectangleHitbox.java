package rpg.api.collision;

import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * This {@link Hitbox} subclass explicitly describing a rectangle.
 * 
 * @author
 */
public class RectangleHitbox extends Hitbox {
	
	public RectangleHitbox(final UnmodifiableVec2D offset, final UnmodifiableVec2D offsetWidth, final UnmodifiableVec2D offsetHeight) {
		super(offset, offsetWidth, offsetHeight);
	}
	public RectangleHitbox(final UnmodifiableVec2D offset, final UnmodifiableVec2D offsetWidth, final UnmodifiableVec2D offsetHeight, final int precision) {
		super(precision, offset, offsetWidth, offsetHeight, offsetWidth.add(offsetHeight));
	}
	
	@Override
	public boolean checkCollision(final Vec2D<?> colliderPoint) {
		return getPosition().getValueX() <= colliderPoint.getValueX() && getPoint(1).getValueX() >= colliderPoint.getValueX() && getPosition().getValueY() <= colliderPoint.getValueY() && getPoint(2).getValueY() >= colliderPoint.getValueY();
	}
}
