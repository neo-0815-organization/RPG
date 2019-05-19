package rpg.api.gfx;

/**
 * Used to differentiate between genders.
 * 
 * @author Neo Hornberger, Tim Ludwig
 */
public enum Gender implements PathModifier {
	FEMALE("female"),
	MALE("male"),
	
	OTHER("");
	
	private final String pathModifier;
	
	private Gender(final String pathModifier) {
		this.pathModifier = pathModifier;
	}
	
	@Override
	public String getPathModifier() {
		return pathModifier;
	}
	
	public Gender getByModifier(final String modifier) {
		for(final Gender gender : values())
			if(gender.pathModifier.equals(modifier)) return gender;
		
		return OTHER;
	}
}
