package rpg.api.gfx;

public enum Gender {
	FEMALE("female"),
	MALE("male"),
	
	OTHER("");
	
	private final String name;
	
	private Gender(final String name) {
		this.name = name;
	}
	
	public String getPath() {
		return "/" + name;
	}
	
	public String getName() {
		return name;
	}
}
