package rpg;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import rpg.api.gfx.IDrawable;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1861206115390613807L;
	private final BufferStrategy strat;
	
	public GameFrame() {
		super("RPG");
		
		setBounds(0, 0, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	
	public Graphics2D getDrawingGraphics() {
		return (Graphics2D) strat.getDrawGraphics();
	}
	
	public void showGraphics() {
		strat.show();
	}
	
	public void drawScene(IDrawable scene) {
		final Graphics2D g2d = getDrawingGraphics();
		
		scene.draw(g2d);
		
		showGraphics();
		g2d.dispose();
	}
}
