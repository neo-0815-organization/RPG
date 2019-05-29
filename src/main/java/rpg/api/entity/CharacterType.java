package rpg.api.entity;

public enum CharacterType {
	NONE(null),
	
	MAGE("illusionist"),
	THIEF("thief"),
	NATURE("natures_guardian");
	
	private final String name;
	
	private CharacterType(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static CharacterType getByName(final String name) {
		for(final CharacterType type : values())
			if(type.name.equals(name)) return type;
		
		return NONE;
	}
}
