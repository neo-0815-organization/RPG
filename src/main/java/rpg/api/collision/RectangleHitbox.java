package rpg.api.collision;

import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

public class RectangleHitbox extends Hitbox{
	public RectangleHitbox(UnmodifiableVec2D offset, UnmodifiableVec2D offSetWidth, UnmodifiableVec2D offsetHeight) {
		super(offset, offSetWidth, offsetHeight, offSetWidth.add(offsetHeight));
	}

	@Override
	public boolean checkCollision(Vec2D<?> colliderPoint) {
		return (getOffset(0).getX().getValuePixel() <= colliderPoint.getX().getValuePixel() && getPoint(1).getX().getValuePixel() >= colliderPoint.getX().getValuePixel()) &&
				(getOffset(0).getY().getValuePixel() <= colliderPoint.getY().getValuePixel() && getPoint(2).getY().getValuePixel() >= colliderPoint.getY().getValuePixel());
	}

//	private UnmodifiableVec2D getWidth() {
//		return getOffset(1);
//	}
//	
//	private UnmodifiableVec2D getHeight() {
//		return getOffset(2);
//	}
	
	@Override
	public String toString() {
		return super.toString()+ " Loc: "+getOffset(0)+ " Width:"+getOffset(1)+ " Height:" + getOffset(2);
	}
	
}