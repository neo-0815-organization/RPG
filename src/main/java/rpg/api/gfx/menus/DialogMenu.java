package rpg.api.gfx.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import rpg.RPG;
import rpg.Statics;
import rpg.api.dialog.Dialog;
import rpg.api.entity.Entity;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class DialogMenu extends Menu {
	private final Dialog dialog;
	private final Entity entity;
	private final BufferedImage entityImage, playerImage;
	private final Graphics2D dialogGraphics;
	
	private int actualLineNumber = 0;
	
	public DialogMenu(final Dialog dialog, final Entity entity) {
		this.dialog = dialog;
		this.entity = entity;
		
		entityImage = ImageUtility.scale(entity.getSprite().getCurrentAnimationFrame(), Statics.scale * 8);
		playerImage = ImageUtility.scale(RPG.gameField.getPlayerController().getPlayer().getSprite().getCurrentAnimationFrame(), Statics.scale * 8);
		
		final int width = Statics.frameSize.width / 2 - Statics.scale(50), height = Statics.scale(260);
		
		JPanel dialogpane = new JPanel();
		dialogpane.setBounds(0, Statics.frameSize.height - height, width, height);
		dialogpane.setBackground(Color.GREEN);
		addComponent(dialogpane);
		
		dialogGraphics = (Graphics2D) dialogpane.getGraphics();
		
		menu.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEY");
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) actualLineNumber++;
			}
		});
		
		setBackground(RPGButton.BUTTON_TEMPLATE);
	}
	
	@Override
	public void draw(final Graphics2D g2d) {
		graphics.drawImage(entityImage, Statics.scale(10), Statics.scale(10), null);
		graphics.drawImage(playerImage, Statics.frameSize.width - Statics.scale(playerImage.getWidth() - 10), Statics.frameSize.height - Statics.scale(playerImage.getHeight() / 1.5), null);
		
		for(int i = 0; i < 198; i++)
			graphics.drawImage(entityImage, 10000, 10000, null);
		
		dialogGraphics.setFont(Statics.defaultFont);
		
		for(int i = 0; i <= actualLineNumber; i++)
			dialogGraphics.drawString(dialog.getLine(i), Statics.scale(20), Statics.scale(i * 25 + 30));
	}
}
