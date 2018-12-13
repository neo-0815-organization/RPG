package rpg.api.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import rpg.RPG;
import rpg.api.entity.Controller;
import rpg.api.entity.Entity;
import rpg.api.entity.LocalController;
import rpg.api.tile.Tile;

/**
 * The class GameField represents the game world.
 *
 * @author Erik Diers, Tim Ludwig, Neo Hornberger
 */
public class GameField extends Scene {
	public static boolean		inGame	= true;
	public static final double  MAX_DELTA_TIME = 0.21,
								MIN_DELTA_TIME = 0.00001;
	private final Background	background;
	
	
	private double	deltaTime;
	private long	lastFrame	= System.currentTimeMillis();
	
	private Thread update, draw;
	
	private LinkedList<Entity> entities = new LinkedList<>();
	private LinkedList<Controller> controller = new LinkedList<>();
	private LocalController playerController;
	
	public GameField() {
		background = new Background();
		
		startUpdating();
		startDrawing();
	}
	
	public GameField(BufferedImage backgorundImage) {
		background = new Background(backgorundImage);
		
		startUpdating();
		startDrawing();
	}
	
	
	@Override
	public void draw(final Graphics2D g2d) {
		background.draw(g2d);
		
		for (Entity e: entities) e.draw(g2d);
	}
	
	/**
	 * Starts a new Thread, which updates the Gamefield
	 */
	private void startUpdating() {
		update = new Thread("GameLoop") {
			@Override
			public void run() {
				while(inGame) {
					deltaTime = (System.currentTimeMillis() - lastFrame) / 1000d;
					lastFrame = System.currentTimeMillis();
					
					
					if(deltaTime > MAX_DELTA_TIME) deltaTime = MAX_DELTA_TIME;
					if(deltaTime <= MIN_DELTA_TIME) deltaTime = MIN_DELTA_TIME;
					
					update(deltaTime);
					
					System.out.print("");
				}
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
		
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).update(deltaTime);
	}
	
	/**
	 * Shuts down the {@link GameField}'s threads //<a href = "https://www.if-schleife.de"> Lol</a>.
	 */
	public void shutDown() {
		update.interrupt();
		draw.interrupt();
	}
	
	/**
	 * ymsdfh.ausfdhg//1 nicer Komment
	 * @param e
	 */
	public void addEntity(Controller c) {
		entities.add(c.getEntity());
		controller.add(c);
	}
	public void addPlayerController(LocalController c) {
		entities.add(c.getEntity());
		playerController = c;
		
	}
}
