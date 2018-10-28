package rpg.api.scene;

import java.awt.Graphics2D;

import rpg.RPG;
import rpg.api.tile.Tile;

/**
 * The class GameField represents the game world.
 *
 * @author Erik Diers, Tim Ludwig, Neo Hornberger
 */
public class GameField extends Scene {
	public static boolean		inGame	= true;
	public final int			maxFPS	= 30;
	private final Background	background;
	
	private double	deltaTime;
	private long	lastFrame	= System.currentTimeMillis();
	
	public GameField() {
		background = new Background();
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		background.draw(g2d);
	}
	
	/**
	 * Starts the game loop.
	 */
	public void startUpdating() {
		new Thread("GameLoop") {
			@Override
			public void run() {
				while(inGame)
					update();
			}
		}.start();
	}
	
	/**
	 * Updates all {@link Tile}s and {@link Entity}s.
	 */
	public void update() {
		deltaTime = (System.currentTimeMillis() - lastFrame) / 1000d;
		
		// TODO klären, ob und wenn, wie
		if(deltaTime < 1D / maxFPS) {
			try {
				Thread.sleep((long) (1000d / maxFPS - deltaTime * 1000d));
			} catch(final InterruptedException e) {
				e.printStackTrace();
			}
			
			deltaTime = (System.currentTimeMillis() - lastFrame) / 1000d;
		}
		
		RPG.gameFrame.drawScene(this);
		System.out.println(deltaTime + " Sollte:" + 1000d / maxFPS);
		
		lastFrame = System.currentTimeMillis();
	}
}
