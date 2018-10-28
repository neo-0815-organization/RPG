package rpg.api.scene;

import java.awt.Graphics2D;

import rpg.RPG;

public class GameField extends Scene {
	public static boolean inGame = true;
	public final int maxFPS = 30;
	private final Background background;
	
	public GameField() {
		background = new Background();
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		background.draw(g2d);
	}
	
	public void startUpdating() {
		double deltaTime = 0;
		long timeLastFrameBegun = System.currentTimeMillis();
		
		while(inGame) {
			deltaTime = (System.currentTimeMillis() - timeLastFrameBegun) / 1000.0;
			
			if(deltaTime < 1D / maxFPS) {
				try {
					Thread.sleep((long) (1000.0 / maxFPS - deltaTime * 1000));
				}catch(final InterruptedException e) {
					e.printStackTrace();
				}
				deltaTime = (System.currentTimeMillis() - timeLastFrameBegun) / 1000.0;
			}
			
			timeLastFrameBegun = System.currentTimeMillis();
			
			RPG.gameFrame.drawScene(this);
			System.out.println(deltaTime + " Sollte:" + 1000.0 / maxFPS);
		}
	}
}
