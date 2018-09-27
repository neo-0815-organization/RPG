package rpg.api;

/**
 * The Class Location.
 */
public class Location {
	private int x, y;
	
	/**
	 * Instantiates a new location.
	 *
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y coordinate.
	 *
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Location@" + Integer.toHexString(hashCode()) + "[" + x + ", " + y + "]";
	}
}
