package rpg.api.gfx;

public enum SpriteTheme {
	DEFAULT("default"),
	NONE("none");
	
	private SpriteTheme(final String name) {
		path = "/assets/textures/" + name;
	}
	
	private String path;
	
	public String getPath() {
		return path;
	}
}
