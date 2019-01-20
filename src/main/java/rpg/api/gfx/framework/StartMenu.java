package rpg.api.gfx.framework;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import rpg.RPG;
import rpg.api.filereading.ResourceGetter;
import rpg.api.gfx.GameFrame;

public class StartMenu extends Menu {
	private static final BufferedImage BACKGROUND_IMAGE = ResourceGetter.getImage("/assets/textures/menu/Hintergrund.png");
	
	public StartMenu() {
		RPGButton singleplayer = new RPGButton("button.singleplayer");
		singleplayer.setBounds(0, 0, 500, 100);
		addComponent(singleplayer);
		
		RPGButton multiplayer = new RPGButton("button.multiplayer");
		multiplayer.setBounds(0, 0, 500, 100);
		addComponent(multiplayer);
		
		RPGButton exit = new RPGButton("button.exit");
		exit.setBounds(0, 100, 500, 100);
		addComponent(exit);
		
		RPGButton options = new RPGButton("button.options");
		options.setBounds(0, 200, 500, 100);
		addComponent(options);
		
		setBackground(BACKGROUND_IMAGE);
	}
	
	public static void main(String[] args) {
		RPG.gameFrame = new GameFrame();
		StartMenu menu = new StartMenu();
		
		menu.show();
	}
}
