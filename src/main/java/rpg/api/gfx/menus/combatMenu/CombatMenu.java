package rpg.api.gfx.menus.combatMenu;

import java.awt.Canvas;
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
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import javafx.geometry.HPos;
import javafx.scene.shape.DrawMode;
import jdk.management.resource.internal.inst.StaticInstrumentation;
import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.LivingEntity;
import rpg.api.entity.item.Weapon;
import rpg.api.entity.item.Weapon.IntRange;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.gfx.menus.combatMenu.TextAnimation.MovingPattern;

public class CombatMenu extends Menu{
	private static CombatAnimationSheet RUN_ANIM = new CombatAnimationSheet("run.png", 4, 0.1, 3);
	
	private ActionControlPanel actionControlPanel;
	private DrawPanel drawPanel;
	
	public CombatResult combatResult;
	
	public CombatMenu(LivingEntity enemy, boolean isEscapeable) {
		drawPanel = new DrawPanel(enemy, this);
//		addComponent(drawPanel); Add itself!

		actionControlPanel = new ActionControlPanel(drawPanel, enemy, isEscapeable, this);
		actionControlPanel.setLocation(0, 600);
		
		addComponent(actionControlPanel, 0);
	}
	
	@Override
	public void close() {
		drawPanel.draw();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		super.close();
	}
	
	public CombatResult getCombatResult() {
		return combatResult;
	}
	
	private static class ActionControlPanel extends JPanel {
		private static final long serialVersionUID = 531341439927510844L;
		private static final int ACTION_COUNT = 3, BUTTON_WIDTH = 400, BUTTON_HEIGHT = 150;
		private static final Dimension SIZE = new Dimension(BUTTON_WIDTH, (BUTTON_WIDTH + 5) * ACTION_COUNT);
		private RPGButton[] actions;
		
		private DrawPanel drawPanel;
		private LivingEntity enemy, player;
		private CombatMenu combatMenu;
		private boolean isEscapeable;
		
		
		private ActionControlPanel(DrawPanel drawPanel, LivingEntity enemy, boolean isEscapeable, CombatMenu combatMenu) {
			setSize(SIZE);
			setLayout(null);
			actions = new RPGButton[ACTION_COUNT];

			this.drawPanel = drawPanel;
			this.enemy = enemy;
			this.player = (LivingEntity) RPG.gameField.getPlayerController().getEntity();
			this.combatMenu = combatMenu;
			this.isEscapeable = isEscapeable;
			
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
				if (buttonTitle == "Run" && !isEscapeable) newButt.setEnabled(false);
				
				newButt.setActionCommand(buttonTitle);
				newButt.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						switch(e.getActionCommand()) {
							case "Attack":
								enablePanel(false);
								System.out.println("[CombatMenu] >> Player: Attack");
								
								
								ActionControlPanel.this.drawPanel.playAnimation(new CombatAnimation(Weapon.ATTACK_ANIMATION_SHEET, drawPanel.enemyLoc.x, drawPanel.enemyLoc.y, false));

								int damage = player.getWeaponDamageRange().getRandom();
								drawPanel.playAnimation(new TextAnimation(""+ damage, drawPanel.enemyLoc.x + 300, drawPanel.enemyLoc.y + 200, 0.3, MovingPattern.LIFTING, Color.RED, 50));
								if (enemy.reduceHP(damage)) {
									combatMenu.combatResult = CombatResult.PLAYER_WON;
									combatMenu.close();
									return;
								}
								
								
								enemyTurn();
								
								enablePanel(true);
								
								break;
							case "Run":
								for (int i = 0; i < 10; i++) {
									drawPanel.playAnimation(new CombatAnimation(RUN_ANIM, Statics.frameSize.width / 2, Statics.frameSize.height / 2, true));
								}
								
								if (new IntRange(0, 100).getRandom() > 50) {
									drawPanel.playAnimation(new TextAnimation("SUCCSESS", Statics.frameSize.width / 2, Statics.frameSize.height / 2, 0.6, MovingPattern.LIFTING, Color.GREEN, 40));
									combatMenu.combatResult = CombatResult.PLAYER_ESCAPED;
									combatMenu.close();
									return;
									
								} else {
									drawPanel.playAnimation(new TextAnimation("MISS", Statics.frameSize.width / 2, Statics.frameSize.height / 2, 0.6, MovingPattern.LIFTING, Color.RED, 40));
									enemyTurn();
								}
								
						}
					}
				});
				actions[i] = newButt;
				add(actions[i]);
			}
		}
		
		public void enablePanel(boolean enable) {
			repaint();
			for (int i = 0; i < ACTION_COUNT; i++) {
				actions[i].setEnabled(enable);
				if (i == 2 && !isEscapeable)actions[i].setEnabled(false);
			}
		}
		
		public void enemyTurn() {
			ActionControlPanel.this.drawPanel.playAnimation(new CombatAnimation(Weapon.ATTACK_ANIMATION_SHEET, drawPanel.playerLoc.x, drawPanel.playerLoc.y, false));
			int damage = enemy.getWeaponDamageRange().getRandom();
			drawPanel.playAnimation(new TextAnimation(""+ damage, drawPanel.playerLoc.x + 300, drawPanel.playerLoc.y + 200, 0.3, MovingPattern.LIFTING, Color.RED, 50));

			if (player.reduceHP(damage)) {
				combatMenu.combatResult = CombatResult.ENEMY_WON;
				combatMenu.close();
				return;
			}
			drawPanel.draw();
		}
	}
	
	
	
	private static class DrawPanel extends Canvas {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8394656885155990598L;

		private BufferStrategy strat;
		
		private static int PLAYERSCALE = 7;
		private BufferedImage playerIMG, enemyIMG;
		private LivingEntity enemy, player;
		
		private CombatAnimation currentAnimation;
		
		public Point playerLoc, enemyLoc;
		
		public DrawPanel(LivingEntity enemy, CombatMenu menuToAdd) {
			setVisible(true);
			menuToAdd.addComponent(this);
			createBufferStrategy(2);
			strat = getBufferStrategy();
			
//			System.out.println(enemy);
			this.enemy = enemy;
			this.player = RPG.gameField.getPlayerController().getPlayer();
			setSize(Statics.frameSize);
			playerIMG = ImageUtility.scale(player.getSprite().getAnimation("walking/left").currentFrame(), Statics.scale * PLAYERSCALE);
			enemyIMG =  ImageUtility.scale(enemy.getSprite().getAnimation("walking/right").currentFrame(), Statics.scale * PLAYERSCALE);
			
			Dimension size = Statics.frameSize;
			playerLoc = new Point(size.width - playerIMG.getWidth() - 20, (int)(size.height * 0.3));
			enemyLoc = new Point((int)(size.width * 0.15), (int)(size.height * 0.15));
			draw();
		}
		
		public void playAnimation(CombatAnimation animation) {
			animation.reset();
			currentAnimation = animation;
			
			
			double timeSinceLastFrame = 0;
			long timeLastFrameBegun = System.currentTimeMillis();
			
			
			while(!animation.update(timeSinceLastFrame)) {
				timeSinceLastFrame = (System.currentTimeMillis() - timeLastFrameBegun) / 1000D;
				timeLastFrameBegun = System.currentTimeMillis();
				draw();
//				System.out.println("[CombatMenu] >> tslf:" + timeSinceLastFrame);
			}
			currentAnimation = null;
			draw();
		}
	
		
		protected void draw() {
//			System.out.println("[ComponentMenu >> DrawPanel] >> Repaint.");
			
			Dimension size = Statics.frameSize;
			
			
			Graphics2D g2d = (Graphics2D) strat.getDrawGraphics();
			
			g2d.setColor(Color.lightGray);
			g2d.fillRect(0, 0, size.width, size.height);
			g2d.drawImage(playerIMG, playerLoc.x, playerLoc.y, null);
			
			drawLiveBar(new Rectangle(playerLoc.x, playerLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), player.getHP(), player.getMaxHP(), g2d);
			
			g2d.drawImage(enemyIMG, enemyLoc.x, enemyLoc.y, null);
			drawLiveBar(new Rectangle(enemyLoc.x, enemyLoc.y - 20,(int) (size.width * 0.2), (int) (size.height * 0.05)), enemy.getHP(), enemy.getMaxHP(), g2d);
			
			if (currentAnimation != null) {
				currentAnimation.draw(g2d);
			}
			
			strat.show();
			g2d.dispose();
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
