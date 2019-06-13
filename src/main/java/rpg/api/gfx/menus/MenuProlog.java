package rpg.api.gfx.menus;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

import rpg.Logger;
import rpg.Statics;
import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.localization.StringLocalizer;

public class MenuProlog extends Menu {
	private static final BufferedImage BACKGROUND = ResourceGetter.getImage("/assets/textures/overlay/porlogBackground.png"),
									CREDTIS = ResourceGetter.getImage("/assets/textures/overlay/Credtis.png");
	
	int speed;
	private String txt = StringLocalizer.localize("prolog.text");
	private String credits = StringLocalizer.localize("credtis.text");
	public JTextArea label;
	private double timeSinceLastFrame, allOverTime;
	
	private final double allOverTimeToReach;
	
	private double y;
	private long timeLastFrameBegun;
	
	public MenuProlog(final double scrollTime, final boolean isProlog) {
		
		
		allOverTimeToReach = scrollTime;
		final int labelWIDTH = 1000;
		label = new JTextArea(txt);
		label.setFont(Statics.defaultFont(80));
		label.setForeground(new Color(0, 0, 0));
		label.setBounds((Statics.frameSize.width - labelWIDTH) / 2 + 200, Statics.frameSize.height, labelWIDTH, 0);
		y = label.getY();
		
		if (isProlog) 
			txt = Statics.formatToWidth(txt, label.getWidth(), label.getFont());
		else 
			txt = credits.replaceAll("<br>", "\n");
		
		int lines = 0;// (int) (txt.length() / (label.getWidth() / (double) charWidth)) + 1;
		
		{
			int openIndex = 0;
			while((openIndex = txt.indexOf("\n", openIndex)) != -1) {
				openIndex++;
				lines++;
			}
		}
		final int height = (int) (lines + Statics.frameSize.height * (isProlog ? 1.5 : 2));
		speed = (int) (height / scrollTime);
		label.setSize(labelWIDTH, lines * label.getFont().getSize() * 2 + 1);
		label.setBackground(new Color(255, 255, 255, 0));
		
		label.setText(txt);
		
		final RPGButton skip = new RPGButton("prolog.skip");
		skip.setBounds(200, 400, 100, 100);
		skip.addActionListener(e -> setOpen(false));
		addComponent(skip);
		
		addComponent(label);
		
		setBackground(isProlog ? BACKGROUND : CREDTIS);
		timeLastFrameBegun = System.currentTimeMillis();
		

	}
	
	@Override
	protected void updateMenu() {
		try {
			Thread.sleep(10);
		}catch(final InterruptedException e) {
			Logger.error(e);
		}
		
		timeSinceLastFrame = (System.currentTimeMillis() - timeLastFrameBegun) / 1000D;
		timeLastFrameBegun = System.currentTimeMillis();
		
		y -= speed * timeSinceLastFrame;
		label.setLocation(label.getX(), (int) y);
		
		allOverTime += timeSinceLastFrame;
		menu.repaint();
		
		if(allOverTime > allOverTimeToReach) setOpen(false);
	}
}
