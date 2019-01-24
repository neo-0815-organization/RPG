package rpg.api.collision;

import rpg.api.entity.Entity;
import rpg.api.tile.Tile;
import rpg.api.vector.UnmodifiableVec2D;
import rpg.api.vector.Vec2D;

/**
 * The class Hitbox used only in collision management.
 * 
 * @author Jan Unterhuber, Tim Ludwig, Neo Hornberger, Erik Diers
 */
public abstract class Hitbox {
	/**
	 * The array of {@link Vec2D} defining this {@link Hitbox}.
	 * {@code offsets[0]} is measured relative to the {@link Entity} or
	 * {@link Tile} represented by this {@link Hitbox}, while
	 * {@code offsets[n] : n > 0} is measured relative to {@code offsets[0]}.
	 */
	protected UnmodifiableVec2D[] offsets;
	
	/**
	 * Constructs a new {@link Hitbox} with the precision {@code 1} and the
	 * defining {@link Vec2D}[] 'points'.
	 * 
	 * @param points
	 *            the {@link Vec2D}[] defining this {@link Hitbox}
	 * @see #Hitbox(int, UnmodifiableVec2D...)
	 */
	protected Hitbox(final UnmodifiableVec2D... points) {
		this(1, points);
	}
	
	/**
	 * Constructs a new {@link Hitbox} with the precision 'precision' and the
	 * defining {@link Vec2D}[] 'points'.
	 * 
	 * @param precision
	 *            the number of reference {@link Vec2D}s used for collision
	 *            checks
	 * @param points
	 *            the {@link Vec2D}[] defining this {@link Hitbox}
	 */
	protected Hitbox(final int precision, final UnmodifiableVec2D... points) {
		offsets = points;
		
		if(precision != 1) {
			final double scalar = 1D / precision;
			final UnmodifiableVec2D[] allOffsets = new UnmodifiableVec2D[points.length * precision];
			for(int i = 0; i < points.length; i++)
				allOffsets[i] = points[i];
			
			for(int i = 0; i < points.length; i++) {
				final UnmodifiableVec2D vec1 = getPoint(i), vec2 = getPoint(i + 1 >= points.length ? 0 : i + 1);
				final UnmodifiableVec2D vec12 = vec2.subtract(vec1);
				for(int j = 0; j < precision - 1; j++)
					allOffsets[points.length + i * (precision - 1) + j] = vec12.scale(scalar * (j + 1));
			}
			offsets = allOffsets;
		}
	}
	
	/**
	 * Checks whether this {@link Hitbox} collides with another {@link Hitbox}.
	 * 
	 * @param colliderHitbox
	 *            the {@link Hitbox} to check for collision with
	 * @param colliderPosition
	 *            the position of the {@link Entity} or {@link Tile} the
	 *            'colliderHitbox' represents, relative to the position of the
	 *            {@link Entity} or {@link Tile} represented by this
	 *            {@link Hitbox}
	 * @return
	 *         <ul>
	 *         <li>{@code true} if this {@link Hitbox} collides with the
	 *         'colliderHitbox'</li>
	 *         <li>{@code false}
	 *         <ul>
	 *         <li>if {@code this} does not collide with the
	 *         'colliderHitbox'</li>
	 *         <li>if the collision check misses the collision</li>
	 *         </ul>
	 *         </ul>
	 */
	public boolean checkCollision(final Hitbox colliderHitbox, final UnmodifiableVec2D colliderPosition) {
		for(int i = 0; i < offsets.length; i++)
			if(colliderHitbox.checkCollision(colliderPosition.subtract(getPoint(i).scale(-1)))) return true;
		
		return false;
	}
	
	/**
	 * Checks whether this {@link Hitbox} collides with a point.
	 * 
	 * @param colliderPoint
	 *            a {@link Vec2D}, relative to the position of the
	 *            {@link Entity} or {@link Tile} represented by this
	 *            {@link Hitbox}, to check for collision
	 * @return {@code true} if this {@link Hitbox} collides with the point
	 *         'colliderPoint'
	 */
	public abstract boolean checkCollision(Vec2D<?> colliderPoint);
	
	/**
	 * Returns the position of a point on the boundary, relative to the position
	 * of the {@link Entity} or {@link Tile} represented by this {@link Hitbox}.
	 * 
	 * @param i
	 *            the index of the point to get
	 * @return an {@link UnmodifiableVec2D}, the i-th point on the boundary of
	 *         this {@link Hitbox}
	 */
	protected UnmodifiableVec2D getPoint(final int i) {
		return getOffset(0).add(i == 0 ? Vec2D.ORIGIN : getOffset(i));
	}
	
	/**
	 * Returns the position of this {@link Hitbox}.
	 * 
	 * @return the position of this {@link Hitbox} relative to the
	 *         {@link Entity} or {@link Tile} represented by this {@link Hitbox}
	 */
	protected UnmodifiableVec2D getPosition() {
		return getOffset(0);
	}
	
	/**
	 * Returns the offset of a point on the boundary of this {@link Hitbox}.
	 * 
	 * @param i
	 *            the index of the offset to get
	 * @return the {@link UnmodifiableVec2D} offset of the i-th point on the
	 *         boundary of this {@link Hitbox}
	 */
	protected UnmodifiableVec2D getOffset(final int i) {
		return offsets[i];
	}
}
