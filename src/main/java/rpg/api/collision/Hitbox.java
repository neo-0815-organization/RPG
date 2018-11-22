package rpg.api.collision;

import rpg.api.entity.Entity;
import rpg.api.tile.Tile;
import rpg.api.vector.ModifiableVec2D;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * @author Jan Unterhuber & Tim Ludwig & Erik Diers
 */
public abstract class Hitbox {
	/**
	 * The array of points represented as a {@link Vec2D} specifying this
	 * {@link Hitbox}. <code>offsets[0]</code> is measured relative to the
	 * {@link Entity} or {@link Tile} being boxed by this {@link Hitbox}, while
	 * <code>offsets[n] | n > 0</code> is measured relative to the {@link Vec2D}
	 * <code>offsets[0]</code>.
	 */
	private UnmodifiableVec2D[] offsets;
	
	/**
	 * Creates a Hitbox.
	 * @param precision means the number of Collision vectors...TODO This need to be Changed
	 * @param points
	 */
	protected Hitbox(int precision, final UnmodifiableVec2D... points) {
		offsets = points;
		
		if (precision != 1) {
			double scalar = 1D / precision;
			UnmodifiableVec2D[] allOffsets = new UnmodifiableVec2D[points.length * (precision)];
			for (int i = 0; i < points.length; i++)
				allOffsets[i] = points[i];
			
			for (int i = 0; i < points.length; i++) {
				UnmodifiableVec2D vec1 = getPoint(i), vec2 = getPoint(i + 1 >= points.length?0:i + 1);
				UnmodifiableVec2D vec12 = vec2.subtract(vec1);
				for (int j = 0; j < precision - 1; j++) {
					allOffsets[points.length + i * (precision - 1) + j] = vec12.scale(scalar * (j + 1));
				}
			}
			offsets = allOffsets;
		}
	}
	
	/**
	 * Checks whether this {@link Hitbox} collides with an other {@link Hitbox}.
	 * 
	 * @param colliderHitbox
	 *            the {@link Hitbox} to check for collision
	 * @param colliderPosition
	 *            the position of the {@link Entity} or {@link Tile} the
	 *            {@link Hitbox} <code>colliderHitbox</code> represents,
	 *            relative to the position of the {@link Entity} or {@link Tile}
	 *            represented by this {@link Hitbox}
	 * @return <code>true</code> if this {@link Hitbox} collides with the
	 *         {@link Hitbox} <code>colliderHitbox</code>
	 */
	public boolean checkCollision(final Hitbox colliderHitbox, final UnmodifiableVec2D colliderPosition) {
		if(this instanceof CircleHitbox || colliderHitbox instanceof CircleHitbox) {
			if(this instanceof CircleHitbox && colliderHitbox instanceof CircleHitbox) {
				final double r1 = ((CircleHitbox) this).radius, r2 = ((CircleHitbox) colliderHitbox).radius;
				return r1 * r1 + 2 * r1 * r2 + r2 * r2 > colliderPosition.add(getOffset(0)).subtract(colliderHitbox.getOffset(0)).magnitudeSquared();
			}else {
				CircleHitbox circle;
				Hitbox nonCircle;
				final ModifiableVec2D collPos = colliderPosition.toModifiable();
				
				if(this instanceof CircleHitbox) {
					circle = (CircleHitbox) this;
					nonCircle = colliderHitbox;
					collPos.scale(-1);
				}else {
					circle = (CircleHitbox) colliderHitbox;
					nonCircle = this;
				}
				
				UnmodifiableVec2D point;
				
				for(int i = 0; i < nonCircle.offsets.length; i++) {
					point = nonCircle.getPoint(i);
					
					if(circle.checkCollision(point.subtract(collPos))) return true;
				}
				return false;
				
			}
		}else for(int i = 0; i < offsets.length; i++)
			if(colliderHitbox.checkCollision(colliderPosition.subtract(getPoint(i).scale(-1)))) return true;
		
		return false;
	}
	
	/**
	 * Checks whether this {@link Hitbox} collides with a point.
	 * 
	 * @param colliderPoint
	 *            the position of a point, relative to the position of the
	 *            {@link Entity} or {@link Tile} represented by this
	 *            {@link Hitbox}, to check for collision, represented as a
	 *            {@link Vec2D}
	 * @return <code>true</code> if this {@link Hitbox} collides with the point
	 *         <code>colliderPoint</code>
	 */
	public abstract boolean checkCollision(Vec2D<?> colliderPoint);
	
	/**
	 * Returns the position of an corner as an {@link UnmodifiableVec2D}
	 * 
	 * @param i
	 * @return
	 */
	protected UnmodifiableVec2D getPoint(final int i) {
		return getOffset(0).add(i == 0 ? Vec2D.ORIGIN : getOffset(i));
	}
	
	protected UnmodifiableVec2D getOffset(final int i) {
		return offsets[i];
	}
	
//	public static void main(String[] args) {
//		RectangleHitbox rect = new RectangleHitbox(UnmodifiableVec2D.ORIGIN, UnmodifiableVec2D.createXY(10, 0), UnmodifiableVec2D.createXY(0, 2), 10);
//		RectangleHitbox rect2 = new RectangleHitbox(UnmodifiableVec2D.ORIGIN, UnmodifiableVec2D.createXY(2, 0), UnmodifiableVec2D.createXY(0, 10), 10);
//		System.out.println(rect.checkCollision(rect2, UnmodifiableVec2D.createXY(-3, 3)));
//	}
}
