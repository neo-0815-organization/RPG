package rpg.api.gfx.menus;

import static rpg.api.gfx.menus.MenuStart.EXIT_IMAGE;
import static rpg.api.gfx.menus.MenuStart.EXIT_IMAGE_FOCUS;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rpg.Logger;
import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.Player;
import rpg.api.entity.PlayerController;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.scene.DevSave;
import rpg.api.scene.Save;
import rpg.api.vector.ModifiableVec2D;

public class MenuSaves extends Menu {
	private static final FileFilter DIR_FILTER = file -> file.isDirectory();
	private static final File SAVES_DIR = Statics.fileFromExecutionDir("saves");
	
	private boolean openGame = false, openCharacterSelection, openDevWorld;
	
	public MenuSaves() {
		final int finalRadius = Statics.scale(85);
		
		final JPanel savesPane = new JPanel();
		savesPane.setOpaque(false);
		savesPane.setLayout(null);
		
		final JScrollPane scroll = new JScrollPane(savesPane);
		scroll.setBounds(0, finalRadius / 2, Statics.frameSize.width, Statics.frameSize.height - finalRadius * 2);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		scroll.getViewport().setOpaque(false);
		addComponent(scroll);
		
		final Consumer<String> startSave = name -> {
			Logger.info("Loading save with name '" + name + "'...");
			
			if(name == null || name == "/dev/") {
				openCharacterSelection = true;
				
				if(name == "/dev/") openDevWorld = true;
			}else {
				(RPG.gameField.save = new Save(name)).load();
				
				openGame = true;
				setOpen(false);
			}
		};
		final ActionListener saveButtonAction = e -> startSave.accept(e.getActionCommand());
		
		if(!SAVES_DIR.exists()) SAVES_DIR.mkdir();
		
		final File[] saves = SAVES_DIR.listFiles(DIR_FILTER);
		for(int i = 0; i < saves.length; i++) {
			final RPGButton saveButton = new RPGButton();
			saveButton.setText(saves[i].getName());
			saveButton.setBounds(0, i * finalRadius, scroll.getWidth(), finalRadius);
			saveButton.disableBorder();
			saveButton.setActionCommand(saves[i].getName());
			saveButton.addActionListener(saveButtonAction);
			
			savesPane.add(saveButton);
		}
		
		final RPGButton create = new RPGButton("save.create");
		create.setBounds(0, Statics.frameSize.height - finalRadius, finalRadius, finalRadius);
		create.disableBorder();
		create.addActionListener(e -> startSave.accept(null));
		addComponent(create);
		
		final RPGButton exit = new RPGButton(EXIT_IMAGE);
		exit.setBounds(Statics.frameSize.width - finalRadius, Statics.frameSize.height - finalRadius, finalRadius, finalRadius);
		exit.disableBorder();
		exit.addActionListener(e -> setOpen(false));
		exit.setFocusImage(EXIT_IMAGE_FOCUS);
		addComponent(exit);
		
		setKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(final KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_F6:
						startSave.accept("/dev/");
						break;
				}
			};
		});
		
		setBackground(RPGButton.BUTTON_TEMPLATE);
	}
	
	@Override
	protected void updateMenu() {
		if(openCharacterSelection) {
			final MenuCharacterSelection charSelect = new MenuCharacterSelection();
			openSubMenu(charSelect);
			
			if(openDevWorld) (RPG.gameField.save = new DevSave()).load();
			else(RPG.gameField.save = new Save()).load();
			
			final Player p = new Player();
			p.setLocation(ModifiableVec2D.createXY(0, 0));
			p.setSprite(charSelect.getSelectedSheet().getSprite());
			
			RPG.gameField.setPlayerController(new PlayerController(p));
			
			openGame = true;
			openCharacterSelection = false;
			openDevWorld = false;
			
			openSubMenu(new MenuProlog(20));
			
			setOpen(false);
		}
	}
	
	public boolean shouldOpenGame() {
		return openGame;
	}
}
