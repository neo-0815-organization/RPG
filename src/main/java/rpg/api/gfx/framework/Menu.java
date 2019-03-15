package rpg.api.gfx.framework;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rpg.RPG;
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
	JPanel menu = new JPanel();
	private boolean isOpen = true;
	
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
	
	@Override
	public void draw(final Graphics2D g2d) {
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
	 * Sets the Background-image
	 * 
	 * @param img
	 */
	public void setBackground(final BufferedImage img) {
		final JLabel label = new JLabel(new ImageIcon(ImageUtility.scale(img, menu.getWidth(), menu.getHeight())));
		label.setBounds(0, 0, menu.getWidth(), menu.getHeight());
		menu.add(label);
	}
	
	/**
	 * start showing the menu. This will freeze the game until the menu is
	 * closed.
	 */
	public final void show() {
		menuCount++;
		while(isOpen) {
			updateMenu();
			RPG.gameFrame.drawScene(this);
		}
		close();
	}
	
	protected void updateMenu() {
		
	}
	
	/**
	 * Closes the menu. When the last menu is closed, the canvas became visible
	 */
	public void close() {
		menuCount--;
		RPG.gameFrame.remove(menu);
		if(menuCount == 0) RPG.gameFrame.setCanvasVisibility(true);
	}
	
	public void openSubMenu(final Menu menu) {
		this.menu.setVisible(false);
		menu.show();
		this.menu.setVisible(true);
	}
}
