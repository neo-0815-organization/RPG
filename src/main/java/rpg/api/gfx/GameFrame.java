package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import rpg.api.scene.Camera;
import rpg.api.scene.Scene;

public class GameFrame extends JFrame {
	private static final long		serialVersionUID	= 1861206115390613807L;
	private final BufferStrategy	drawBuffStrat;
	
	public GameFrame() {
		super("RPG");
		
		setBounds(0, 0, (int) Camera.frameSize.getWidth(), (int) Camera.frameSize.getHeight());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent we) {
				// Code to run on WindowCloseing
				System.exit(0);
			}
		});
		
		setUndecorated(false);
		setResizable(false);
		
		setVisible(true);
		
		createBufferStrategy(2);
		drawBuffStrat = getBufferStrategy();
		
	}
	
	public Graphics2D getDrawingGraphics() {
		Graphics2D g2d = (Graphics2D) drawBuffStrat.getDrawGraphics();
		g2d.scale(Camera.scale, Camera.scale);
		return g2d;
	}
	
	public void showGraphics() {
		drawBuffStrat.show();
	}
	
	public void drawScene(final Scene scene) {
		final Graphics2D g2d = getDrawingGraphics();
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		scene.draw(g2d);
		
		showGraphics();
		g2d.dispose();
	}
}
