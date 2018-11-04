package rpg.api.vector;

import rpg.api.units.DistanceValue;

public abstract class Vec2D<T extends Vec2D<?>> implements Cloneable {
	public static final UnmodifiableVec2D ORIGIN = UnmodifiableVec2D.createXY(0, 0);
	
	protected double x, y;
	
	/**
	 * Creates a new {@link Vec2D} using the carthesian coordinate system.
	 * 
	 * @param x
	 *            the x component of the new {@link Vec2D}
	 * @param y
	 *            the y component of the new {@link Vec2D}
	 */
	protected Vec2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the sum of this {@link Vec2D} and the parameter.
	 *
	 * @param vec
	 *            the {@link Vec2D} to add
	 * @return the sum of this {@link Vec2D} and the parameter
	 */
	public abstract T add(Vec2D<?> vec);
	
	/**
	 * Calculates the difference of this {@link Vec2D} and the parameter.
	 *
	 * @param vec
	 *            the {@link Vec2D} to subtract
	 * @return the difference of this {@link Vec2D} and the parameter
	 */
	public abstract T subtract(final Vec2D<?> vec);
	
	/**
	 * Scales this {@link Vec2D} by the parameter.
	 *
	 * @param d
	 *            the value to scale with
	 * @return the scaled version of this {@link Vec2D}
	 */
	public abstract T scale(final double d);
	
	/**
	 * Calculates the dotproduct of this {@link Vec2D} and the parameter.
	 * German: Skalarprodukt
	 *
	 * @param vec
	 *            the {@link Vec2D} to multiply with
	 * @return the dotproduct
	 */
	public double dotproduct(final Vec2D<?> vec) {
		return x * vec.x + y * vec.y;
	}
	
	/**
	 * Calculates the magnitude of this {@link Vec2D}.
	 *
	 * @return the magnuitude
	 */
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Calculates the squared magnitude of this {@link Vec2D}.
	 *
	 * @return the squared magnitude
	 */
	public double magnitudeSquared() {
		return x * x + y * y;
	}
	
	/**
	 * Gets the x component of this {@link Vec2D}.
	 *
	 * @return the x component of this {@link Vec2D}
	 */
	public DistanceValue getX() {
		return new DistanceValue(x);
	}
	
	/**
	 * Gets the y component of this {@link Vec2D}.
	 *
	 * @return the y component of this {@link Vec2D}
	 */
	public DistanceValue getY() {
		return new DistanceValue(y);
	}
	
	/**
	 * Returns a human readable representation of this {@link Vec2D} looking
	 * like Vec2D@hash[x, y]
	 * 
	 * @return the textual representation of this {@link Vec2D}
	 */
	@Override
	public String toString() {
		return super.toString() + "[" + x + ", " + y + "]";
	}
	
	public ModifiableVec2D toModifiable() {
		return new ModifiableVec2D(x, y);
	}
	
	public UnmodifiableVec2D toUnmodifiable() {
		return new UnmodifiableVec2D(x, y);
	}
	
	@Override
	public Vec2D<?> clone() {
		try {
			return (Vec2D<?>) super.clone();
		}catch(final CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return ORIGIN;
	}
}
