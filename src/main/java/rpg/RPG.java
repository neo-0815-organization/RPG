package rpg;

/**
 * The main class of this project.
 */
public class RPG {
	public static final int	SCREEN_WIDTH	= 1680,
			SCREEN_HEIGHT = 1050,
			TILE_SIZE = 32;
	public static GameFrame	gui;
	
	public static void main(final String[] args) {
		init();
	}
	
	/* This method should not be edited! Thanks! */
	private static void init() {
		gui = new GameFrame();
	}
}
