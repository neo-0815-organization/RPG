package rpg.api.vector;

public class ModifiableVec2D extends Vec2D<ModifiableVec2D> {
	
	/**
	 * Creates a new {@link ModifiableVec2D} using the polar coordinate system.
	 * 
	 * @param a
	 *            the angle of the new {@link ModifiableVec2D}
	 * @param l
	 *            the length of the new {@link ModifiableVec2D}
	 */
	public static ModifiableVec2D createAL(final double a, final double l) {
		return new ModifiableVec2D(Math.cos(a) * l, Math.sin(a) * l);
	}
	
	/**
	 * Creates a new {@link ModifiableVec2D} using the carthesian coordinate
	 * system.
	 * 
	 * @param x
	 *            the x component of the new {@link ModifiableVec2D}
	 * @param y
	 *            the y component of the new {@link ModifiableVec2D}
	 */
	public static ModifiableVec2D createXY(final double x, final double y) {
		return new ModifiableVec2D(x, y);
	}
	
	protected ModifiableVec2D(final double x, final double y) {
		super(x, y);
	}
	
	@Override
	public ModifiableVec2D add(final Vec2D<?> vec) {
		x += vec.x;
		y += vec.y;
		
		return this;
	}
	
	@Override
	public ModifiableVec2D subtract(final Vec2D<?> vec) {
		x -= vec.x;
		y -= vec.y;
		
		return this;
	}
	
	@Override
	public ModifiableVec2D scale(final double d) {
		x *= d;
		y *= d;
		
		return this;
	}
	
	public void setX(final double x) {
		this.x = x;
	}
	
	public void setY(final double y) {
		this.y = y;
	}
}
