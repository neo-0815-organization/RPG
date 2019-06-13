package rpg.api.entity;

public enum CharacterType {
	NONE(null, false),
	
	MAGE("illusionist", true),
	THIEF("thief", true),
	NATURE("natures_guardian", true),
	
	DWARFT_GUARIAN_TYPE("dwarfs/dwarf_city/entities/guardian.png", false);
	
	private final String name;
	private final boolean hasWalkanim;
	
	private CharacterType(final String name, final boolean hasWalkanimation) {
		this.name = name;
		this.hasWalkanim = hasWalkanimation;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasWalkanimation() {
		return hasWalkanim;
	}
	
	
	public static CharacterType getByName(final String name) {
		for(final CharacterType type : values())
			if(type.name.equals(name)) return type;
		
		return NONE;
	}
}
