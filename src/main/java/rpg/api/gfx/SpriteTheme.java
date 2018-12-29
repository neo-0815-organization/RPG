package rpg.api.gfx;

/**
 * Used to differentiate between different thematic areas in
 * {@link Sprite}-loading.
 *
 * @author Tim Ludwig, Neo Hornberger, Alexander Schallenberg
 */
public enum SpriteTheme {
	// Zwerge
	MOERSBERGE("dwarfs/moersberge"),
	CASTLE_MOERSBERG("dwarfs/castle_moersberg"),
	DWARG_CITY("dwarfs/dwarf_city"),
	MOERSBERGWERKE("dwarfs/moersbergwerke"),
	
	// Feen
	SCHALLENBERGE("fairies/schallenberge"),
	//	HUEBEL("fairies/huebel"),
	
	// Oreanen
	SEYHAN("oreans/seyhan"),
	
	// Gestrandete
	DUEHNE("stranded/duehne"),
	
	// Maracus
	HORNBERGE("maracus/hornberge"),
	
	// Bach
	BROMBACH("creek/brombach"),
	
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
