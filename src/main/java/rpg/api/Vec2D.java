package rpg.api;

/**
 * The Class Vec2D representing a two dimensional vector.
 *
 * @author Tim Ludwig, Neo Hornberger
 */
public class Vec2D {
	private double x, y;
	
	public static Vec2D create(double x, double y) {
		return new Vec2D(x, y);
	}
	
	/**
	 * Creates a new {@link Vec2D} using the carthesian coordinate system.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	private Vec2D(final double x, final double y) {
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
	public Vec2D add(Vec2D vec) {
		return create(x + vec.x, y + vec.y);
	}
	
	/**
	 * Calculates the difference of this {@link Vec2D} and the parameter.
	 *
	 * @param vec
	 *            the {@link Vec2D} to subtract
	 * @return the difference of this {@link Vec2D} and the parameter
	 */
	public Vec2D subtract(Vec2D vec) {
		return create(x - vec.x, y - vec.y);
	}
	
	/**
	 * Calculates the scalar product of this {@link Vec2D} and the parameter.
	 *
	 * @param d
	 *            the value to scale with
	 * @return the scalar product of this {@link Vec2D} and the parameter
	 */
	public Vec2D scale(double d) {
		return create(x * d, y * d);
	}
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate.
	 *
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the x coordinate and updates the polar coordinates.
	 *
	 * @param x
	 *            the new x coordinate
	 */
	public void setX(final double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate and updates the polar coordinates.
	 *
	 * @param y
	 *            the new y coordinate
	 */
	public void setY(final double y) {
		this.y = y;
	}
	
	/**
	 * Creates a clone of this {@link Vec2D}.
	 * 
	 * @return the newly created {@link Vec2D}
	 */
	@Override
	public Vec2D clone() {
		return new Vec2D(x, y);
	}
}
