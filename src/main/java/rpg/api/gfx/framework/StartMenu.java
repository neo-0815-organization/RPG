package rpg.api.gfx.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import rpg.api.filereading.ResourceGetter;
import rpg.api.scene.Camera;


/**
 * This menu is showing up once the game is started.
 * @author EriDie
 *
 */
public class StartMenu extends Menu {
	private static final BufferedImage BACKGROUND_IMAGE = ResourceGetter.getImage("/assets/textures/menu/Hintergrund.png"), 
									   SETTINGS_IMAGE = ResourceGetter.getImage("/assets/textures/menu/button_options.png"),
									   EXIT_IMAGE =  ResourceGetter.getImage("/assets/textures/menu/button_exit.png");
	
	public StartMenu() {
		RPGButton singleplayer = new RPGButton("button.singleplayer");
		singleplayer.setBounds(0, 0, 500, 100);
		singleplayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(singleplayer);
		
		RPGButton multiplayer = new RPGButton("button.multiplayer");
		multiplayer.setBounds(Camera.frameSize.width - 500, 0, 500, 100);
		addComponent(multiplayer);
		
		final int finalRadius = 128;
		
		RPGButton exit = new RPGButton(EXIT_IMAGE);
		exit.setBounds(Camera.frameSize.width - finalRadius, Camera.frameSize.height - finalRadius, finalRadius, finalRadius);
		exit.disableBorder();
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		addComponent(exit);
		
		
		RPGButton options = new RPGButton(SETTINGS_IMAGE);
		options.setBounds(0, Camera.frameSize.height - finalRadius, finalRadius, finalRadius);
		options.disableBorder();
		addComponent(options);
		
		setBackground(BACKGROUND_IMAGE);
	}
	
//	public static void main(String[] args) {
//		RPG.gameFrame = new GameFrame();
//		StartMenu menu = new StartMenu();
//		
//		menu.show();
//	}
}
