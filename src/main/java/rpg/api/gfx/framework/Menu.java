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

public class Menu extends Scene{
	JPanel menu = new JPanel();
	private boolean isOpen = true;
	
	
	public Menu() {
		menu.setBounds(0, 0, RPG.gameFrame.getWidth(), RPG.gameFrame.getHeight());
		RPG.gameFrame.add(menu, 0);
		RPG.gameFrame.revalidate();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		RPG.gameFrame.repaint();
	}
	
	public void setOpen(boolean flag) {
		isOpen = flag;
	}
	
	public void addComponent(JComponent c) {
		menu.add(c);
	}
	
	public void setBackground(BufferedImage img) {
		JLabel label = new JLabel(new ImageIcon(ImageUtility.scale(img, menu.getWidth(), menu.getHeight())));
		label.setBounds(0, 0, menu.getWidth(), menu.getHeight());
		menu.add(label);
	}
	
	public void show() {
		while (isOpen) {
			RPG.gameFrame.drawScene(this);
		}
	}
	
}
