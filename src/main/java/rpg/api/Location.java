package rpg.api;

/**
 * The Class Location.
 */
public class Location {
	private int x,
			y;
	
	/**
	 * Instantiates a new {@link Location}.
	 *
	 * @param x
	 *        the x coordinate
	 * @param y
	 *        the y coordinate
	 */
	public Location(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate of this {@Link Location}.
	 *
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate.
	 * 
	 * @param x
	 *        the x coordinate to set
	 */
	public void setX(final int x) {
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
	 * Sets the y coordinate.
	 * 
	 * @param y
	 *        the y to set
	 */
	public void setY(final int y) {
		this.y = y;
	}
	
	/**
	 * Returns a human readable representation of this {@link Location} looking like
	 * Location@hash[x; y].
	 * 
	 * @return the textual representation of this {@link Location}
	 */
	@Override
	public String toString() {
		return "Location@" + Integer.toHexString(hashCode()) + "[" + x + ", " + y + "]";
	}
}
