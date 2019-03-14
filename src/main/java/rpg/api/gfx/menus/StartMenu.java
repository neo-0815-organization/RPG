package rpg.api.gfx.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import rpg.Statics;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

/**
 * This menu is showing up once the game is started.
 * 
 * @author EriDie
 *
 */
public final class StartMenu extends Menu {
	private static final BufferedImage BACKGROUND_IMAGE = ResourceGetter.getImage("/assets/textures/menu/background.png"), SETTINGS_IMAGE = ResourceGetter.getImage("/assets/textures/menu/button_options.png"), SETTINGS_IMAGE_FOCUS = ResourceGetter.getImage("/assets/textures/menu/button_options_activated.png");
	static final BufferedImage EXIT_IMAGE = ResourceGetter.getImage("/assets/textures/menu/button_exit.png"), EXIT_IMAGE_FOCUS = ResourceGetter.getImage("/assets/textures/menu/button_exit_activated.png");
	
	private boolean openPref;
	
	public StartMenu() {
		final int rectWidth = Statics.scale(310), rectHeight = Statics.scale(80);
		
		final RPGButton singleplayer = new RPGButton("button.singleplayer");
		singleplayer.setBounds(0, 0, rectWidth, rectHeight);
		singleplayer.disableBorder();
		singleplayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(singleplayer);
		
		final RPGButton multiplayer = new RPGButton("button.multiplayer");
		multiplayer.setBounds(Statics.frameSize.width - rectWidth, 0, rectWidth, rectHeight);
		multiplayer.disableBorder();
		addComponent(multiplayer);
		
		final int finalRadius = Statics.scale(85);
		
		final RPGButton exit = new RPGButton(EXIT_IMAGE);
		exit.setBounds(Statics.frameSize.width - finalRadius, Statics.frameSize.height - finalRadius, finalRadius, finalRadius);
		exit.disableBorder();
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setFocusImage(EXIT_IMAGE_FOCUS);
		addComponent(exit);
		
		final RPGButton options = new RPGButton(SETTINGS_IMAGE);
		options.setBounds(0, Statics.frameSize.height - finalRadius, finalRadius, finalRadius);
		options.disableBorder();
		options.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				openPref = true;
			}
		});
		options.setFocusImage(SETTINGS_IMAGE_FOCUS);
		addComponent(options);
		
		setBackground(BACKGROUND_IMAGE);
	}
	
	@Override
	public void updateMenu() {
		if(openPref) {
			openSubMenu(new PreferencesMenu());
			openPref = false;
		}
	}
	
	//	public static void main(String[] args) {
	//		RPG.gameFrame = new GameFrame();
	//		StartMenu menu = new StartMenu();
	//
	//		menu.show();
	//	}
}
