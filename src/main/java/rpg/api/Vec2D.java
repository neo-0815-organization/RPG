package rpg.api;

/**
 * The Class Vec2D representing a two dimensional vector.
 *
 * @author Tim Ludwig, Neo Hornberger
 */
public class Vec2D {
	private double angle;
	private double length;
	
	private double x;
	
	private double y;
	
	/**
	 * Creates a new {@link Vec2D} using the carthesian coordinate system.
	 *
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the newly constructed {@link Vec2D}
	 */
	public static Vec2D createXY(final double x, final double y) {
		return new Vec2D(x, y);
	}
	
	/**
	 * Creates a new {@link Vec2D} using the polar coordinate system.
	 *
	 * @param angle
	 *            the angle
	 * @param length
	 *            the length
	 * @return the newly constructed {@link Vec2D}
	 */
	public static Vec2D createAL(final double angle, final double length) {
		final double x = length * Math.cos(angle);
		final double y = length * Math.sin(angle);
		
		return new Vec2D(x, y);
	}
	
	/**
	 * @see #createXY(double, double)
	 */
	private Vec2D(final double x, final double y) {
		this.x = x;
		this.y = y;
		length = Math.sqrt(x * x + y * y);
		angle = Math.atan(y / this.x);
	}
	
	/**
	 * Calculates the sum of this {@link Vec2D} and the parameter.
	 *
	 * @param vec
	 *            the {@link Vec2D} to add
	 * @return the sum of this {@link Vec2D} and the parameter
	 */
	public Vec2D add(Vec2D vec) {
		return createXY(x + vec.x, y + vec.y);
	}
	
	/**
	 * Calculates the difference of this {@link Vec2D} and the parameter.
	 *
	 * @param vec
	 *            the {@link Vec2D} to subtract
	 * @return the difference of this {@link Vec2D} and the parameter
	 */
	public Vec2D subtract(Vec2D vec) {
		return createXY(x - vec.x, y - vec.y);
	}
	
	/**
	 * Calculates the scalar product of this {@link Vec2D} and the parameter.
	 *
	 * @param d
	 *            the value to scale with
	 * @return the scalar product of this {@link Vec2D} and the parameter
	 */
	public Vec2D scale(double d) {
		return createAL(angle, length * d);
	}
	
	/**
	 * Gets the angle.
	 *
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public double getLength() {
		return length;
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
	 * Sets the angle and updates the carthesian coordinates.
	 *
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(final double angle) {
		this.angle = angle;
		x = length * Math.cos(angle);
		y = length * Math.sin(angle);
	}
	
	/**
	 * Sets the length and updates the carthesian coordinates.
	 *
	 * @param length
	 *            the new length
	 */
	public void setLength(final double length) {
		this.length = length;
		x = length * Math.cos(angle);
		y = length * Math.sin(angle);
	}
	
	/**
	 * Sets the x coordinate and updates the polar coordinates.
	 *
	 * @param x
	 *            the new x coordinate
	 */
	public void setX(final double x) {
		this.x = x;
		length = Math.sqrt(x * x + y * y);
		angle = Math.atan(y / x);
	}
	
	/**
	 * Sets the y coordinate and updates the polar coordinates.
	 *
	 * @param y
	 *            the new y coordinate
	 */
	public void setY(final double y) {
		this.y = y;
		length = Math.sqrt(x * x + y * y);
		angle = Math.atan(y / x);
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
