package rpg.api.scene;

import java.awt.Graphics2D;

public class GameField extends Scene {
	private Background background;
	
	@Override
	public void draw(Graphics2D g2d) {
		background.draw(g2d);
	}
}
