package rpg.api.gfx.menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import rpg.Statics;
import rpg.api.entity.Person;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class CharacterSelectMenu extends Menu {
	private static BufferedImage maleIMG = ResourceGetter.getImage("/assets/textures/menu/button_male.png"), femaleIMG = maleIMG;
	RPGButton male, female, thief, fairy, magican, startGame;
	
	boolean maleSelected = true;
	int currentSelected;
	
	public CharacterSelectMenu() {
		Dimension size = Statics.frameSize;
		male = new RPGButton(maleIMG);
		male.setBounds((int)(size.width * 0.4), 800, 200, 200);
		male.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				maleSelected = true;
				updateButtons();
			}
		});
		addComponent(male);
		
		female = new RPGButton(femaleIMG);
		female.setBounds((int)(size.width * 0.4), 800, 200, 200);
		female.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				maleSelected = false;
				updateButtons();
			}
		});
		addComponent(female);
		
		female = new RPGButton(maleIMG);
		female.setBounds((int)(size.width * 0.7), 800, 200, 200);
		female.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				maleSelected = false;
				updateButtons();
			}
		});
		addComponent(female);
		
		fairy = new RPGButton();
		fairy.setBounds(size.width * 0.1, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		fairy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSelected = 0;
			}
		});
		addComponent(fairy);
		
		thief = new RPGButton();
		thief.setBounds(size.width * 0.4, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		thief.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSelected = 1;
			}
		});
		addComponent(thief);
		
		magican = new RPGButton();
		magican.setBounds(size.width * 0.7, size.height * 0.1, size.width * 0.2, size.height * 0.5);
		magican.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentSelected = 2;
			}
		});
		addComponent(magican);
		
		startGame = new RPGButton("Start Game");
		startGame.setBounds(size.width - 200, size.height - 200, 200, 200);
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(startGame);
		
		updateButtons();
	}
	
	public void updateButtons() {
		if (maleSelected) {
			fairy.setBackgroundImage(Person.CharacterSheet.PLAYER_FAIRY_MALE.getSprite().getCurrentAnimationFrame());
			thief.setBackgroundImage(Person.CharacterSheet.PLAYER_THIEF_MALE.getSprite().getCurrentAnimationFrame());
			magican.setBackgroundImage(Person.CharacterSheet.PLAYER_MAGICAN_MALE.getSprite().getCurrentAnimationFrame());
		} else {
			fairy.setBackgroundImage(Person.CharacterSheet.PLAYER_FAIRY_FEMALE.getSprite().getCurrentAnimationFrame());
			thief.setBackgroundImage(Person.CharacterSheet.PLAYER_THIEF_FEMALE.getSprite().getCurrentAnimationFrame());
			magican.setBackgroundImage(Person.CharacterSheet.PLAYER_MAGICAN_FEMALE.getSprite().getCurrentAnimationFrame());
		}
	}
}
