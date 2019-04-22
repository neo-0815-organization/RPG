package rpg.api.gfx.menus.combatMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SecondaryLoop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import jdk.management.resource.internal.inst.StaticInstrumentation;
import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.LivingEntity;
import rpg.api.entity.item.Weapon;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class CombatMenu extends Menu{
	
	ActionControlPanel actionControlPanel;
	DrawPanel drawPanel;
	
	public CombatMenu(LivingEntity enemy) {
		drawPanel = new DrawPanel(enemy);
		addComponent(drawPanel);

		actionControlPanel = new ActionControlPanel(drawPanel);
		actionControlPanel.setLocation(0, 600);
		
		addComponent(actionControlPanel);
		
	}
	
	
	private static class ActionControlPanel extends JPanel {
		private static final int ACTION_COUNT = 3, BUTTON_WIDTH = 400, BUTTON_HEIGHT = 150;
		private static final Dimension SIZE = new Dimension(BUTTON_WIDTH, (BUTTON_WIDTH + 5) * ACTION_COUNT);
		private RPGButton[] actions;
		
		private DrawPanel drawPanel;
		
		private ActionControlPanel(DrawPanel drawPanel) {
			setSize(SIZE);
			setLayout(null);
			actions = new RPGButton[ACTION_COUNT];

			this.drawPanel = drawPanel;
			
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
				newButt.setActionCommand(buttonTitle);
				newButt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						switch(e.getActionCommand()) {
							case "Attack":
								System.out.println("[CombatMenu] >> Attackanimation started.");
								drawPanel.playAnimation(new CombatAnimation(Weapon.ATTACK_ANIMATION_SHEET, drawPanel.enemyLoc.x, drawPanel.enemyLoc.y, false));
								//GOON HERE
								break;
						}
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
		
		private CombatAnimation currentAnimation;
		
		public Point playerLoc, enemyLoc;
		
		public DrawPanel(LivingEntity enemy) {
//			System.out.println(enemy);
			this.enemy = enemy;
			this.player = RPG.gameField.getPlayerController().getPlayer();
			setSize(Statics.frameSize);
			playerIMG = ImageUtility.scale(player.getSprite().getAnimation("walking/left").currentFrame(), Statics.scale * PLAYERSCALE);
			enemyIMG =  ImageUtility.scale(enemy.getSprite().getAnimation("walking/right").currentFrame(), Statics.scale * PLAYERSCALE);
			
			Dimension size = Statics.frameSize;
			playerLoc = new Point(size.width - playerIMG.getWidth() - 20, (int)(size.height * 0.3));
			enemyLoc = new Point((int)(size.width * 0.15), (int)(size.height * 0.15));
		}
		
		public void playAnimation(CombatAnimation animation) {
			animation.reset();
			currentAnimation = animation;
			
			
			double timeSinceLastFrame = 0;
			long timeLastFrameBegun = System.currentTimeMillis();
			
			SecondaryLoop secLoop = Toolkit.getDefaultToolkit().getSystemEventQueue().createSecondaryLoop();
			secLoop.enter();
			
			while(!animation.update(timeSinceLastFrame)) {
				timeSinceLastFrame = (System.currentTimeMillis() - timeLastFrameBegun) / 1000D;
				timeLastFrameBegun = System.currentTimeMillis();
				validate();
				repaint();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("[CombatMenu] >> tslf:" + timeSinceLastFrame);
			}
			repaint();
			currentAnimation = null;
			secLoop.exit();
		}
		
		@Override
		public void paint(Graphics g) {
			paintComponent(g);
		}
	
		@Override
		protected void paintComponent(Graphics g) {
			System.out.println("[ComponentMenu >> DrawPanel] >> Repaint.");
			
			Dimension size = Statics.frameSize;
			
			
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(playerIMG, playerLoc.x, playerLoc.y, null);
			
			player.setHP(5);
			drawLiveBar(new Rectangle(playerLoc.x, playerLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), player.getHP(), player.getMaxHP(), g2d);
			
			g2d.drawImage(enemyIMG, enemyLoc.x, enemyLoc.y, null);
			drawLiveBar(new Rectangle(enemyLoc.x, enemyLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), enemy.getHP(), enemy.getMaxHP(), g2d);
			
			if (currentAnimation != null) {
				currentAnimation.draw(g2d);
			}
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
