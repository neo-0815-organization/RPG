package rpg.api.gfx;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import rpg.Statics;
import rpg.api.listener.key.KeyboardSensor;
import rpg.api.scene.Scene;

/**
 * The gameframe is the instance to display all the graphics. It is working with
 * a Canvas for normal drawing, which is not visible, while menu are opend
 * 
 * @author Erik Diers, Neo Hornberger
 */
public class GameFrame extends JFrame {
	private static final long		serialVersionUID	= 1861206115390613807L;
	private static final boolean	fullScreen			= true;
	
	private final Canvas			canvas;
	private final BufferStrategy	drawBuffStrat;
	
	public GameFrame() {
		super("RPG");
		
		setSize(Statics.frameSize.width, Statics.frameSize.height);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(final WindowEvent we) {
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
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		getContentPane().add(canvas);
		
		setVisible(true);
		
		canvas.createBufferStrategy(2);
		drawBuffStrat = canvas.getBufferStrategy();
	}
	
	/**
	 * Returns a Graphics2D instance, in which the content can be drawn. The
	 * Graphics2D need to be shown in order to display them.<br>
	 * ! This needs to be used wisely ! <br>
	 * ! Dont forget to dispose Graphics !
	 * 
	 * @return Graphics2D
	 */
	public Graphics2D getDrawingGraphics() {
		return (Graphics2D) drawBuffStrat.getDrawGraphics();
	}
	
	/**
	 * Displays Graphics2D on the screen.
	 * 
	 * @see #getDrawingGraphics()
	 */
	public void showGraphics() {
		drawBuffStrat.show();
	}
	
	/**
	 * Sets the visibility of the canvas, this should only be used by menus.
	 * 
	 * @param visibility
	 */
	public void setCanvasVisibility(final boolean visibility) {
		canvas.setVisible(visibility);
	}
	
	/**
	 * Draws a Scene on the screen.
	 * 
	 * @param scene
	 */
	public void drawScene(final Scene scene) {
		final Graphics2D g2d = getDrawingGraphics();
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.scale(Statics.scale, Statics.scale);
		scene.draw(g2d);
		
		showGraphics();
		g2d.dispose();
	}
}
