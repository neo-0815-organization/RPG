package rpg.api.gfx.menus;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import rpg.Logger;
import rpg.Statics;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.localization.StringLocalizer;

public class Prolog extends Menu {
	private static final long serialVersionUID = -3906467387005786401L;
	
	int speed;
	private String txt = StringLocalizer.localize("prolog.text");
	public JTextArea label;
	private double timeSinceLastFrame, allOverTime;
	
	private final double allOverTimeToReach;
	
	private double y;
	private long timeLastFrameBegun;
	
	public Prolog(final double scrollTime) {
		allOverTimeToReach = scrollTime;
		final int labelWIDTH = 1000;
		label = new JTextArea(txt);
		label.setFont(Statics.defaultFont(80));
		label.setForeground(new Color(200, 200, 203));
		label.setBounds((Statics.frameSize.width - labelWIDTH) / 2 + 200, Statics.frameSize.height, labelWIDTH, 0);
		y = label.getY();
		
		
		
		txt = Statics.formatToWidth(txt, label.getWidth(), label.getFont());
		
		int lines = 0;//(int) (txt.length() / (label.getWidth() / (double) charWidth)) + 1;
		
		{
			int openIndex = 0;
			while((openIndex = txt.indexOf("\n", openIndex)) != -1) {
				openIndex++;
				lines++;
			}
		}
		final int height = (int) (lines + Statics.frameSize.height * 1.5);
		speed = (int) (height / scrollTime);
		label.setSize(labelWIDTH, lines * label.getFont().getSize() * 2 + 1);
		label.setBackground(new Color(255, 255, 255, 0));
		
		label.setText(txt);
		
		RPGButton skip = new RPGButton("skip");
		skip.setBounds(200, 400, 100, 100);
		skip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setOpen(false);
			}
		});
		
		skip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				while (skip.getBounds().contains(MouseInfo.getPointerInfo().getLocation())) {
					skip.setLocation(skip.getX() +(int) ( 2 * (e.getX() > skip.getWidth() / 2 ? -(skip.getHeight() - e.getX()) : e.getX()) ), skip.getY() + (int) ( 2 * (e.getY() > skip.getHeight() / 2 ? -(skip.getHeight() - e.getY()) : e.getY()) ) );
				}
				skip.repaint();
			}
		});
		addComponent(skip);
		
		addComponent(label);
		
		setBackground(RPGButton.BUTTON_TEMPLATE);
		timeLastFrameBegun = System.currentTimeMillis();
		
	}
	
	@Override
	protected void updateMenu() {
		try {
			Thread.sleep(10);
		}catch(final InterruptedException e) {
			Logger.error(e.getMessage());
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
