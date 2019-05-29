package rpg.api.gfx.menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import rpg.Statics;
import rpg.api.entity.CharacterSheet;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.localization.StringLocalizer;

public class CharacterSelectMenu extends Menu {
	private static BufferedImage maleIMG = ResourceGetter.getImage("/assets/textures/menu/button_male.png"), femaleIMG = ResourceGetter.getImage("/assets/textures/menu/button_female.png");
	RPGButton male, female, thief, natureGuardian, magican, startGame;
	
	boolean maleSelected = true;
	int currentSelected;
	
	public CharacterSelectMenu() {
		final Dimension size = Statics.frameSize;
		male = new RPGButton(maleIMG);
		male.setBounds((int) (size.width * 0.35), 800, 200, 200);
		male.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				maleSelected = true;
				updateButtons();
			}
		});
		addComponent(male);
		
		female = new RPGButton(femaleIMG);
		female.setBounds((int) (size.width * 0.55), 800, 200, 200);
		female.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				maleSelected = false;
				updateButtons();
			}
		});
		addComponent(female);
		
		//		female = new RPGButton(maleIMG);
		//		female.setBounds((int)(size.width * 0.7), 800, 200, 200);
		//		female.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				maleSelected = false;
		//				updateButtons();
		//			}
		//		});
		//		addComponent(female);
		
		natureGuardian = new RPGButton();
		natureGuardian.setBounds(size.width * 0.1, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		natureGuardian.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				currentSelected = 0;
			}
		});
		addComponent(natureGuardian);
		
		thief = new RPGButton();
		thief.setBounds(size.width * 0.4, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		thief.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				currentSelected = 1;
			}
		});
		addComponent(thief);
		
		magican = new RPGButton();
		magican.setBounds(size.width * 0.7, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		magican.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				currentSelected = 2;
			}
		});
		addComponent(magican);
		
		startGame = new RPGButton("start_game");
		startGame.setBounds(size.width - 200, size.height - 200, 200, 200);
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(startGame);
		
		updateButtons();
	}
	
	public void updateButtons() {
		if(maleSelected) {
			natureGuardian.setBackgroundImage(CharacterSheet.PLAYER_NATUREGUARDIAN_MALE.getSprite().getCurrentAnimationFrame());
			natureGuardian.setToolTipText(CHAR_INFO_NATURE_MALE);
			thief.setBackgroundImage(CharacterSheet.PLAYER_THIEF_MALE.getSprite().getCurrentAnimationFrame());
			thief.setToolTipText(CHAR_INFO_THIEF_MALE);
			magican.setBackgroundImage(CharacterSheet.PLAYER_MAGICAN_MALE.getSprite().getCurrentAnimationFrame());
			magican.setToolTipText(CHAR_INFO_WIZZARD_MALE);
		}else {
			natureGuardian.setBackgroundImage(CharacterSheet.PLAYER_NATUREGUARDIAN_FEMALE.getSprite().getCurrentAnimationFrame());
			natureGuardian.setToolTipText(CHAR_INFO_NATURE_FEMALE);
			thief.setBackgroundImage(CharacterSheet.PLAYER_THIEF_FEMALE.getSprite().getCurrentAnimationFrame());
			thief.setToolTipText(CHAR_INFO_THIEF_FEMALE);
			magican.setBackgroundImage(CharacterSheet.PLAYER_MAGICAN_FEMALE.getSprite().getCurrentAnimationFrame());
			magican.setToolTipText(CHAR_INFO_WIZZARD_FEMALE);
		}
	}
	
	public CharacterSheet getSelectedSheet() {
		if(maleSelected) switch(currentSelected) {
			case 0:
				return CharacterSheet.PLAYER_NATUREGUARDIAN_MALE;
			case 1:
				return CharacterSheet.PLAYER_THIEF_MALE;
			case 2:
				return CharacterSheet.PLAYER_MAGICAN_MALE;
		}
		else switch(currentSelected) {
			case 0:
				return CharacterSheet.PLAYER_NATUREGUARDIAN_FEMALE;
			case 1:
				return CharacterSheet.PLAYER_THIEF_FEMALE;
			case 2:
				return CharacterSheet.PLAYER_MAGICAN_FEMALE;
		}
		throw new IllegalArgumentException("Invalid selected");
	}
	
	private static final String CHAR_INFO_THIEF_MALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.thief.male"), 500, Statics.defaultFont(10))
							, CHAR_INFO_THIEF_FEMALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.thief.female"), 500, Statics.defaultFont(10))
							, CHAR_INFO_NATURE_MALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.nature.male"), 500, Statics.defaultFont(10))
							, CHAR_INFO_NATURE_FEMALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.nature.female"), 500, Statics.defaultFont(10))
							, CHAR_INFO_WIZZARD_MALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.wizzard.male"), 500, Statics.defaultFont(10))
							, CHAR_INFO_WIZZARD_FEMALE = Statics.formatToWidthAsHTML(StringLocalizer.localize("char.info.wizzard.female"), 500, Statics.defaultFont(10));
}
