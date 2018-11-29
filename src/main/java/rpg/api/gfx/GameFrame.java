package rpg.api.gfx;

import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import rpg.api.scene.Camera;

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
		
		setUndecorated(true);
		setResizable(false);
		
		createBufferStrategy(2);
		drawBuffStrat = getBufferStrategy();
		
		setVisible(true);
	}
	
	public Graphics2D getDrawingGraphics() {
		return (Graphics2D) drawBuffStrat.getDrawGraphics();
	}
	
	public void showGraphics() {
		drawBuffStrat.show();
	}
	
	public void drawScene(final IDrawable scene) {
		final Graphics2D g2d = getDrawingGraphics();
		
		scene.draw(g2d);
		
		showGraphics();
		g2d.dispose();
	}
}
