package rpg.api.gfx;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import rpg.api.listener.key.KeyboardSensor;
import rpg.api.scene.Camera;
import rpg.api.scene.Scene;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1861206115390613807L;
	private final BufferStrategy drawBuffStrat;
	private final boolean fullScreen = true;
	
	public GameFrame() {
		super("RPG");
		
		setBounds(0, 0, (int) Camera.frameSize.getWidth(), (int) Camera.frameSize.getHeight());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent we) {
				// code to run on WindowClosing
				System.exit(0);
			}
		});
		
		addKeyListener(new KeyboardSensor());
		addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(final FocusEvent e) {
				e.getComponent().requestFocus();
			}
		});
		
		setUndecorated(fullScreen);
		setResizable(false);
		
		final Canvas c = new Canvas();
		getContentPane().add(c);
		
		setVisible(true);
		
		c.createBufferStrategy(2);
		drawBuffStrat = c.getBufferStrategy();
	}
	
	public Graphics2D getDrawingGraphics() {
		final Graphics2D g2d = (Graphics2D) drawBuffStrat.getDrawGraphics();
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
