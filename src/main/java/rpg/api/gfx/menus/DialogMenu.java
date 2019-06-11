package rpg.api.gfx.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import rpg.RPG;
import rpg.Statics;
import rpg.api.dialog.Dialog;
import rpg.api.entity.Entity;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class DialogMenu extends Menu {
	
	private static final int    MAX_LINE_NUMBER = 13;
	private final Dialog        dialog;
	private final BufferedImage entityImage, playerImage;
	private final Graphics2D    dialogGraphics;

	private int actualLineNumber = 0;

	public DialogMenu(final Dialog dialog, final Entity entity) {
		this.dialog = dialog;

		entityImage = ImageUtility.scale(entity.getSprite().getAnimation("walking/down").currentFrame(), 11);
		playerImage = ImageUtility.scale(RPG.gameField.getPlayerController().getPlayer().getSprite().getAnimation("walking/down").currentFrame(), 11);

		final int width = Statics.frameSize.width / 2 - 50, height = 360;

		final JPanel dialogpane = new JPanel();
		dialogpane.setBounds(0, Statics.frameSize.height - height, width, height);
		dialogpane.setBackground(Color.GREEN);
		addComponent(dialogpane);

		dialogGraphics = (Graphics2D) dialogpane.getGraphics();

		setKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						if(actualLineNumber < dialog.getLineCount()) actualLineNumber++;

						dialogGraphics.clearRect(0, 0, width, height);
						dialogGraphics.setColor(Color.GREEN);
						dialogGraphics.fillRect(0, 0, width, height);
						dialogGraphics.setColor(Color.BLACK);
						break;
					case KeyEvent.VK_SPACE:
						setOpen(false);
						break;
				}
			}
		});

		setBackground(RPGButton.BUTTON_TEMPLATE);
	}

	private final int IMAGE_MARGIN = 40;

	@Override
	public void draw(final DrawingGraphics g) {
		graphics.drawImage(entityImage, IMAGE_MARGIN, IMAGE_MARGIN, null);
		graphics.drawImage(playerImage, Statics.frameSize.width - playerImage.getWidth() - IMAGE_MARGIN, Statics.frameSize.height - playerImage.getHeight() - IMAGE_MARGIN, null);

		// for(int i = 0; i < 198; i++)
		// graphics.drawImage(entityImage, 10000, 10000, null);

		dialogGraphics.setFont(Statics.defaultFont);
		
		for(int line = actualLineNumber > MAX_LINE_NUMBER ? actualLineNumber - MAX_LINE_NUMBER : 0, offset = 0; line <= actualLineNumber; line++, offset++)
			dialogGraphics.drawString(dialog.getLine(line), 20, offset * 25 + 30);
	}
}
