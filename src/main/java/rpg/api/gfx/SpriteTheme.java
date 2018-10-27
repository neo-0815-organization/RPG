package rpg.api.gfx;

/**
 * Used to differentiate between different thematic areas in {@link Sprite}
 * loading
 * 
 * @author tludwig
 *         27.10.2018
 */
public enum SpriteTheme {
	DEFAULT("default"),
	NONE("none");
	
	private SpriteTheme(final String name) {
		path = "/assets/textures/" + name;
	}
	
	private String path;
	
	/**
	 * Gets the path corresponding to this {@link SpriteTheme}
	 * 
	 * @return the path to all {@link Sprite}s corresponding to this
	 *         {@link SpriteTheme}
	 */
	public String getPath() {
		return path;
	}
}
