package rpg.api.gfx;

/**
 * Used to differentiate between different thematic areas in
 * {@link Sprite}-loading.
 *
 * @author Tim Ludwig
 */
public enum SpriteTheme {
	// Zwerge
	MOERSBERGE("moersberge"),
	BURG_MOERSBERG("burg_moersberg"),
	STADT_MOERSBERG("stadt_moersberg"),
	MOERSBERGWERKE("moersbergwerke"),
	
	// Feen
	SCHALLENBERGE("schallenberge"),
	//	HUEBEL("huebel"),
	
	// Oreanen
	SEYHAN("seyhan"),
	
	// Gestrandete
	DUEHNE("duehne"),
	
	// Marakus
	HORNBERGE("hornberge"),
	
	// Bach
	BROMBACH("brombach"),
	
	NONE("none"),
	TEST("test");
	
	private SpriteTheme(final String name) {
		path = "/assets/textures/" + name;
	}
	
	private String path;
	
	/**
	 * Gets the path corresponding to this {@link SpriteTheme}.
	 *
	 * @return the path to all {@link Sprite}s corresponding to this
	 *         {@link SpriteTheme}
	 */
	public String getPath() {
		return path;
	}
}
