package rpg.api.gfx.framework;

import java.awt.Container;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import rpg.RPG;
import rpg.api.gfx.GameFrame;
import rpg.api.scene.Scene;

public class Menu extends Scene{
	JPanel menu = new JPanel();
	private boolean isOpen = true;
	
	@Override
	public void draw(Graphics2D g2d) {
		menu.paintAll(g2d);
	}
	
	public void setOpen(boolean flag) {
		isOpen = flag;
	}
	
	public void addComponent(JComponent c) {
		menu.add(c);
	}
	
	public void show() {
		while (isOpen)
			RPG.gameFrame.drawScene(this);
	}
	
	public static void main(String[] args) {
		RPG.gameFrame = new GameFrame();
		Menu m = new Menu();
		m.addComponent(new JButton("LoL"));
		m.menu.setVisible(true);
		m.menu.setBounds(300, 300, 300, 300);;
		m.show();
	}

}
