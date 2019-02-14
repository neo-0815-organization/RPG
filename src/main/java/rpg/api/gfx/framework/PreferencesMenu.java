package rpg.api.gfx.framework;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

import rpg.RPG;
import rpg.api.filereading.ResourceGetter;
import rpg.api.localization.Locale;
import rpg.api.localization.StringLocalizer;
import rpg.api.scene.Camera;

public class PreferencesMenu extends Menu{
	private static final BufferedImage ENGLISH = ResourceGetter.getImage("/assets/textures/menu/language/language_english.png"), 
									   GERMAN  = ResourceGetter.getImage("/assets/textures/menu/language/language_german.png");
	private static String SETTING_FILE = "H:/Desktop/settings.preferences";
	private boolean englishSelected;
	private JLabel counter;
	private int count;
	
	public PreferencesMenu() {
		englishSelected = true;
		RPGButton language = new RPGButton(ENGLISH);
		language.setBounds(500, 200, 512, 256);
		language.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				englishSelected = !englishSelected;
				count++;
				counter.setText("Count: " + count);
				changeLanguage(englishSelected);
				if (englishSelected) {
					((RPGButton)e.getSource()).setBackgroundImage(ENGLISH);
				} else {
					((RPGButton)e.getSource()).setBackgroundImage(GERMAN);

				}
			}
		});
		addComponent(language);
		
		counter = new JLabel("");
		counter.setFont(new Font("Serif", 0, 40));
		counter.setBounds(30, 950, 500, 100);
		
		addComponent(counter);
		
		final int finalRadius = 128;
		
		RPGButton exit = new RPGButton(StartMenu.EXIT_IMAGE);
		exit.setBounds(Camera.frameSize.width - finalRadius, Camera.frameSize.height - finalRadius, finalRadius, finalRadius);
		exit.disableBorder();
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setOpen(false);
			}
		});
		addComponent(exit);

		setBackground(RPGButton.BUTTON_TEMPLATE);
	}
	
	public void changeLanguage(boolean englishSelected) {
		if (englishSelected) {
			StringLocalizer.setActiveLocale(Locale.AMERICAN_ENGLISH);
		} else {
			StringLocalizer.setActiveLocale(Locale.GERMAN);
		}
	}
	
	public void save() {
		File pref = new File(SETTING_FILE);
		if (!pref.exists())
			try {
				pref.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			FileWriter writer = new FileWriter(pref);
			
//			for(String key : map.keySet()) {
//				writer.write(key + "=" + map.get(key));
//			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void close() {
		save();
		super.close();
	}
	
}
