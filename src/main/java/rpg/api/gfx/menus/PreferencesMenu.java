package rpg.api.gfx.menus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JLabel;

import rpg.Statics;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.localization.Locale;
import rpg.api.localization.StringLocalizer;

public class PreferencesMenu extends Menu {
	private static final BufferedImage ENGLISH = ResourceGetter.getImage("/assets/textures/menu/language/language_english.png"), GERMAN = ResourceGetter.getImage("/assets/textures/menu/language/language_german.png");
	private static String SETTING_FILE = "H:/Desktop/settings.preferences";
	private boolean englishSelected;
	private final JLabel counter;
	private final RPGButton language;
	private int count;
	
	public PreferencesMenu() {
		englishSelected = true;
		language = new RPGButton(ENGLISH);
		language.setBounds(500, 200, 512, 256);
		language.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				englishSelected = !englishSelected;
				count++;
				counter.setText("Count: " + count);
				
				changeLanguage(englishSelected);
				
				if(englishSelected) ((RPGButton) e.getSource()).setBackgroundImage(ENGLISH);
				else((RPGButton) e.getSource()).setBackgroundImage(GERMAN);
			}
		});
		addComponent(language);
		
		counter = new JLabel("");
		counter.setFont(new Font("Serif", 0, 40));
		counter.setBounds(30, 950, 500, 100);
		
		addComponent(counter);
		
		final int finalRadius = Statics.scale(85);
		
		final RPGButton exit = new RPGButton(StartMenu.EXIT_IMAGE);
		exit.setBounds(Statics.frameSize.width - finalRadius, Statics.frameSize.height - finalRadius, finalRadius, finalRadius);
		exit.disableBorder();
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(exit);
		
		setBackground(RPGButton.BUTTON_TEMPLATE);
		
		load();
	}
	
	public void changeLanguage(final boolean englishSelected) {
		if(englishSelected) StringLocalizer.setActiveLocale(Locale.AMERICAN_ENGLISH);
		else StringLocalizer.setActiveLocale(Locale.GERMAN);
	}
	
	public void save() {
		final File pref = new File(SETTING_FILE);
		
		if(!pref.exists()) try {
			pref.createNewFile();
		}catch(final IOException e) {
			e.printStackTrace();
		}
		
		try {
			final Formatter formatter = new Formatter(pref);
			formatter.format("%b %d", englishSelected, count);
			
			formatter.close();
			
			//			RPGFileWriter.writeMap(pref, map, "=");
		}catch(final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		final File pref = new File(SETTING_FILE);
		
		try {
			final Scanner sc = new Scanner(pref);
			englishSelected = sc.nextBoolean();
			count = sc.nextInt();
			
			//			map = RPGFileReader.readLineSplit(pref.getPath(), "=");
			
			if(englishSelected) language.setBackgroundImage(ENGLISH);
			else language.setBackgroundImage(GERMAN);
			
			sc.close();
		}catch(final FileNotFoundException e) {
			System.err.println("No settings file found. Created a new one!");
		}
		
	}
	
	@Override
	public void close() {
		save();
		super.close();
	}
	
}
