package rpg.api.gfx.menus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.LivingEntity;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class CombatMenu extends Menu{
	
	ActionControlPanel actionControlPanel;
	DrawPanel drawPanel;
	
	public CombatMenu(LivingEntity enemy) {
		actionControlPanel = new ActionControlPanel();
		actionControlPanel.setLocation(0, 600);
		
		addComponent(actionControlPanel);
		
		drawPanel = new DrawPanel(enemy);
		addComponent(drawPanel);
	}
	
	
	private static class ActionControlPanel extends JPanel {
		private static final int ACTION_COUNT = 3, BUTTON_WIDTH = 400, BUTTON_HEIGHT = 150;
		private static final Dimension SIZE = new Dimension(BUTTON_WIDTH, (BUTTON_WIDTH + 5) * ACTION_COUNT);
		private RPGButton[] actions;
		
		private ActionControlPanel() {
			setSize(SIZE);
			setLayout(null);
			actions = new RPGButton[ACTION_COUNT];
			
			for (int i = 0; i < ACTION_COUNT; i++) {
				String buttonTitle;
				
				switch(i) {
				case 0:
					buttonTitle = "Attack";
					break;
				case 1:
					buttonTitle = "Use Item";
					break;
				case 2:
					buttonTitle = "Run";
					break;
					
				default:
					buttonTitle = "INVALID";
				}
			
				RPGButton newButt = new RPGButton(buttonTitle);
				newButt.setBounds(0, i * (BUTTON_HEIGHT + 5), BUTTON_WIDTH, BUTTON_HEIGHT);
				newButt.setActionCommand(i+"");
				newButt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				actions[i] = newButt;
				add(actions[i]);
			}
		}
	}
	
	private static class DrawPanel extends JPanel {
		private static int PLAYERSCALE = 7;
		private BufferedImage playerIMG, enemyIMG;
		
		public DrawPanel(LivingEntity enemy) {
			System.out.println(enemy);
			setSize(Statics.frameSize);
			playerIMG = ImageUtility.scale(RPG.gameField.getPlayerController().getPlayer().getSprite().getAnimation("walking/left").currentFrame(), Statics.scale * PLAYERSCALE);
			enemyIMG =  ImageUtility.scale(enemy.getSprite().getAnimation("walking/right").currentFrame(), Statics.scale * PLAYERSCALE);
		}
	
		@Override
		protected void paintComponent(Graphics g) {
			Dimension size = Statics.frameSize;
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(playerIMG, size.width - playerIMG.getWidth() - 20, (int)(size.height * 0.3), null);
			g2d.drawImage(enemyIMG, (int)(size.width * 0.15), (int)(size.height * 0.15), null);
					
		}
	}
}
