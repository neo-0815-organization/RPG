package rpg.api.gfx;

/**
 * Used to differentiate between different thematic areas in
 * {@link Sprite}-loading.
 *
 * @author Tim Ludwig, Neo Hornberger, Alexander Schallenberg
 */
public enum SpriteTheme implements IPathModifier{
	// Zwerge
	DWARF_CITY("dwarfs/dwarf_city"),
		CASTLE_MOERSBERG("dwarfs/castle_moersberg"),
		SMELTERY("dwarfs/smeltery"),
		SMITHERY("dwarfs/smithery"),
	MOERSBERGE("dwarfs/moersberge"),
	MOERSBERGWERKE("dwarfs/moersbergwerke"),
	
	// Feen(/Oreanen)
	SCHALLENBERGE("fairies/schallenberge"),
		SEYHAN("fairies/seyhan"),
	SWAMP("fairies/swamp"),
	
	// Gestrandete
	DUNE("stranded/dune"),
	
	// Maracus
	HORNBERGE("maracus/hornberge"),
	
	// Bach
	BROMBACH("creek/brombach"),
	
	NONE("none"),
	TEST("test");
	
	private SpriteTheme(final String name) {
		pathModifier = name;
	}
	
	private String pathModifier;
	
	@Override
	public String getPathModifier() {
		return pathModifier;
	}
	
	@Override
	public String getPrePath() {
		return "/assets/textures/";
	}
}
