package rpg.api.scene;

import java.awt.Graphics2D;
import java.util.ArrayList;

import rpg.RPG;
import rpg.api.entity.Entity;
import rpg.api.tile.Tile;

/**
 * The class GameField represents the game world.
 *
 * @author Erik Diers, Tim Ludwig, Neo Hornberger
 */
public class GameField extends Scene {
	public static boolean		inGame	= true;
	public static final double  maxDeltaTime = 0.21;
	private final Background	background;
	
	
	private double	deltaTime;
	private long	lastFrame	= System.currentTimeMillis();
	
	private Thread update, draw;
	
	private ArrayList<Entity> entities = new ArrayList<>();
	
	
	public GameField() {
		background = new Background();
		startUpdating();
		startDrawing();
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		background.draw(g2d);
		
		for (Entity e: entities)e.draw(g2d);
	}
	
	/**
	 * Starts a new Thread, which updates the Gamefield
	 */
	private void startUpdating() {
		update = new Thread("GameLoop") {
			@Override
			public void run() {
				while(inGame)
					deltaTime = (System.currentTimeMillis() - lastFrame) / 1000d;
					lastFrame = System.currentTimeMillis();
					
					if(deltaTime > maxDeltaTime) deltaTime = maxDeltaTime;
					
					update(deltaTime);
			}
		};
		update.start();
	}
	
	/**
	 * Starts the loop as a thread that draws everything. 
	 */
	private void startDrawing() {
		GameField me = this;
		draw = new Thread("Draw") {
			
			@Override
			public void run() {
				while (inGame) {
					RPG.gameFrame.drawScene(me);
				}
			}
		};
		draw.start();
	}
	
	/**
	 * Updates all {@link Tile}s and {@link Entity}s.
	 */
	private void update(double deltaTime) {
		background.updateBackground(deltaTime);
		for (Entity e:entities)e.update(deltaTime);
	}
	
	/**
	 * Shuts down the {@link GameField}'s threads <a href = "https://www.bmw.de"> Lol</a>.
	 */
	public void shutDown() {
		update.interrupt();
		draw.interrupt();
	}
}
