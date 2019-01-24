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
 * Abstract class for all kinds of menus
 * !Menu freezes the game and disables the Canvas!
 * 
 * @author gruppe 3
 *
 */
public class Menu extends Scene{
	private static int menuCount;
	JPanel menu = new JPanel();
	private boolean isOpen = true;
	
	/**
	 * Creats a new Menu -Nice
	 */
	public Menu() {
		menu.setBounds(0, 0, RPG.gameFrame.getWidth(), RPG.gameFrame.getHeight());
		
		RPG.gameFrame.setCanvasVisibility(false);
		RPG.gameFrame.add(menu);
		RPG.gameFrame.revalidate();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		RPG.gameFrame.repaint();
	}
	
	/**
	 * Sets whether the menu is open and refreshes itself
	 * @param flag [true|false]
	 */
	public void setOpen(boolean flag) {
		isOpen = flag;
	}
	
	/**
	 * This menu adds a default Java component to the menu
	 * @param c
	 */
	public void addComponent(JComponent c) {
		menu.add(c);
	}
	
	/**
	 * Sets the Background-image
	 * @param img
	 */
	public void setBackground(BufferedImage img) {
		JLabel label = new JLabel(new ImageIcon(ImageUtility.scale(img, menu.getWidth(), menu.getHeight())));
		label.setBounds(0, 0, menu.getWidth(), menu.getHeight());
		menu.add(label);
	}
	
	/**
	 * start showing the menu.
	 * This will freeze the game until the menu is closed.
	 */
	public final void show() {
		menuCount++;
		while (isOpen) {
			RPG.gameFrame.drawScene(this);
		}
		close();
	}
	
	/**
	 * Closes the menu. When the last menu is closed, the canvas became visible
	 */
	public void close() {
		menuCount--;
		RPG.gameFrame.remove(menu);
		if (menuCount == 0) {
			RPG.gameFrame.setCanvasVisibility(true);
		}
	}
	
}
