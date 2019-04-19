package rpg.api.gfx.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
		private LivingEntity enemy, player;
		
		
		public DrawPanel(LivingEntity enemy) {
//			System.out.println(enemy);
			this.enemy = enemy;
			this.player = RPG.gameField.getPlayerController().getPlayer();
			setSize(Statics.frameSize);
			playerIMG = ImageUtility.scale(player.getSprite().getAnimation("walking/left").currentFrame(), Statics.scale * PLAYERSCALE);
			enemyIMG =  ImageUtility.scale(enemy.getSprite().getAnimation("walking/right").currentFrame(), Statics.scale * PLAYERSCALE);
		}
	
		@Override
		protected void paintComponent(Graphics g) {
			Dimension size = Statics.frameSize;
			Point playerLoc = new Point(size.width - playerIMG.getWidth() - 20, (int)(size.height * 0.3)),
				  enemyLoc = new Point((int)(size.width * 0.15), (int)(size.height * 0.15));
			
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(playerIMG, playerLoc.x, playerLoc.y, null);
			
			player.setHP(5);
			drawLiveBar(new Rectangle(playerLoc.x, playerLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), player.getHP(), player.getMaxHP(), g2d);
			
			g2d.drawImage(enemyIMG, enemyLoc.x, enemyLoc.y, null);
			drawLiveBar(new Rectangle(enemyLoc.x, enemyLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), enemy.getHP(), enemy.getMaxHP(), g2d);

		}
		
		public void drawLiveBar(Rectangle size, int hp, int maxHP, Graphics2D g2d) {
			g2d.setColor(Color.RED);
			g2d.fill(size);
			g2d.setColor(Color.GREEN);
			g2d.fillRect(size.x, size.y, (int) (size.width * (hp / (double) maxHP)), size.height);
			g2d.setColor(Color.BLACK);
			g2d.draw(size);
		}
	}
	
	
}
