package rpg.api.vector;

/**
 * The Class Vec2D representing a two dimensional vector.
 *
 * @author Tim Ludwig, Neo Hornberger
 */
public class UnmodifiableVec2D extends Vec2D<UnmodifiableVec2D> {
	
	/**
	 * Creates a new {@link UnmodifiableVec2D} using the polar coordinate
	 * system.
	 * 
	 * @param a
	 *            the angle of the new {@link UnmodifiableVec2D}
	 * @param l
	 *            the length of the new {@link UnmodifiableVec2D}
	 */
	public static UnmodifiableVec2D createAL(final double a, final double l) {
		return new UnmodifiableVec2D(Math.cos(a) * l, Math.sin(a) * l);
	}
	
	/**
	 * Creates a new {@link UnmodifiableVec2D} using the carthesian coordinate
	 * system.
	 * 
	 * @param x
	 *            the x component of the new {@link UnmodifiableVec2D}
	 * @param y
	 *            the y component of the new {@link UnmodifiableVec2D}
	 */
	public static UnmodifiableVec2D createXY(final double x, final double y) {
		return new UnmodifiableVec2D(x, y);
	}
	
	protected UnmodifiableVec2D(final double x, final double y) {
		super(x, y);
	}
	
	@Override
	public UnmodifiableVec2D add(final Vec2D<?> vec) {
		return createXY(x + vec.x, y + vec.y);
	}
	
	@Override
	public UnmodifiableVec2D subtract(final Vec2D<?> vec) {
		return createXY(x - vec.x, y - vec.y);
	}
	
	@Override
	public UnmodifiableVec2D scale(final double d) {
		return createXY(x * d, y * d);
	}
}
