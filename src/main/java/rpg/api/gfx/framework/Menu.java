package rpg.api.gfx.framework;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rpg.RPG;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ImageUtility;
import rpg.api.scene.Scene;

/**
 * Abstract class for all kinds of menus !Menu freezes the game and disables the
 * Canvas!
 * 
 * @author Erik Diers, Jan Unterhuber
 *
 */
public class Menu extends Scene {
	private static int menuCount;
	
	private boolean isOpen = true;
	private KeyAdapter keyListener;
	
	protected JPanel menu = new JPanel();
	protected Graphics2D graphics;
	
	/**
	 * Creates a new {@link Menu}
	 */
	public Menu() {
		menu.setBounds(0, 0, RPG.gameFrame.getWidth(), RPG.gameFrame.getHeight());
		
		RPG.gameFrame.setCanvasVisibility(false);
		RPG.gameFrame.add(menu);
		RPG.gameFrame.revalidate();
		
		menu.setLayout(null);
	}
	
	/**
	 * Don't use the parameter! Use {@code graphics}!
	 */
	@Override
	public void draw(final DrawingGraphics g) {
		RPG.gameFrame.repaint();
	}
	
	/**
	 * Sets whether the menu is open and refreshes itself
	 * 
	 * @param flag
	 *            [true|false]
	 */
	public void setOpen(final boolean flag) {
		isOpen = flag;
	}
	
	/**
	 * This menu adds a default Java component to the menu
	 * 
	 * @param c
	 */
	public void addComponent(final JComponent c) {
		menu.add(c);
	}
	
	/**
	 * This menu adds a default Java component to the menu
	 * 
	 * @param c
	 * @param position
	 *            of the Component SEE JPanel::add
	 */
	public void addComponent(final JComponent c, final int index) {
		menu.add(c, index);
	}
	
	/**
	 * This menu adds a default Component to the menu
	 * 
	 * @param c
	 */
	
	public void addComponent(final Component c) {
		menu.add(c);
	}
	
	/**
	 * Sets the Background-image
	 * 
	 * @param img
	 */
	public void setBackground(final BufferedImage img) {
		final JLabel label = new JLabel(new ImageIcon(ImageUtility.scale(img, menu.getWidth(), menu.getHeight())));
		label.setBounds(0, 0, menu.getWidth(), menu.getHeight());
		menu.add(label);
		
		menu.repaint();
	}
	
	/**
	 * Sets the @link{KeyListener}
	 * 
	 * @param keyListener
	 */
	public void setKeyListener(final KeyAdapter keyListener) {
		RPG.gameFrame.removeKeyListener(this.keyListener);
		
		this.keyListener = keyListener;
		
		RPG.gameFrame.addKeyListener(this.keyListener);
	}
	
	/**
	 * start showing the menu. This will freeze the game until the menu is
	 * closed.
	 */
	public final void show() {
		menuCount++;
		
		graphics = (Graphics2D) menu.getGraphics();
		while(isOpen) {
			updateMenu();
			
			RPG.gameFrame.drawScene(this);
		}
		
		close0();
	}
	
	protected void updateMenu() {
		
	}
	
	/**
	 * Closes the menu. When the last menu is closed, the canvas became visible
	 */
	protected void close0() {
		menuCount--;
		
		isOpen = false;
		RPG.gameFrame.remove(menu);
		
		if(menuCount == 0) RPG.gameFrame.setCanvasVisibility(true);
	}
	
	public void openSubMenu(final Menu menu) {
		this.menu.setVisible(false);
		
		menu.show();
		
		this.menu.setVisible(true);
	}
}
