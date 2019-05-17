package rpg.api.gfx.menus.combatMenu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import rpg.Logger;
import rpg.RPG;
import rpg.Statics;
import rpg.api.entity.LivingEntity;
import rpg.api.entity.item.Weapon.IntRange;
import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.ImageUtility;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;
import rpg.api.gfx.menus.combatMenu.TextAnimation.MovingPattern;

public class CombatMenu extends Menu {
	private static CombatAnimationSheet RUN_ANIM = new CombatAnimationSheet("run.png", 4, 0.1, 3), ATTACK_ANIMATION_SHEET = new CombatAnimationSheet("attack.png", 4, 0.75 / 4d, 4);
	
	private final ActionControlPanel actionControlPanel;
	private final DrawPanel drawPanel;
	
	public CombatResult combatResult;
	
	public CombatMenu(final LivingEntity enemy, final boolean isEscapeable) {
		drawPanel = new DrawPanel(enemy, this);
		//		addComponent(drawPanel); Add itself!
		
		actionControlPanel = new ActionControlPanel(drawPanel, enemy, isEscapeable, this);
		actionControlPanel.setLocation(0, 600);
		
		addComponent(actionControlPanel, 0);
	}
	
	@Override
	protected void close0() {
		drawPanel.draw();
		
		try {
			Thread.sleep(3000);
		}catch(final InterruptedException e) {
			e.printStackTrace();
		}
		
		super.close0();
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
		
		private ActionControlPanel(final DrawPanel drawPanel, final LivingEntity enemy, final boolean isEscapeable, final CombatMenu combatMenu) {
			setSize(SIZE);
			setLayout(null);
			actions = new RPGButton[ACTION_COUNT];
			
			this.drawPanel = drawPanel;
			this.enemy = enemy;
			player = (LivingEntity) RPG.gameField.getPlayerController().getEntity();
			this.combatMenu = combatMenu;
			this.isEscapeable = isEscapeable;
			
			final ActionListener actionListener = new ActionListener() {
				
				@Override
				public void actionPerformed(final ActionEvent e) {
					switch(e.getActionCommand()) {
						case "attack":
							enablePanel(false);
							Logger.debug("[CombatMenu] >> Player: Attack");
							
							ActionControlPanel.this.drawPanel.playAnimation(new CombatAnimation(ATTACK_ANIMATION_SHEET, drawPanel.enemyLoc.x, drawPanel.enemyLoc.y, false));
							
							final int damage = player.getWeaponDamageRange().getRandom();
							drawPanel.playAnimation(new TextAnimation("" + damage, drawPanel.enemyLoc.x + 300, drawPanel.enemyLoc.y + 200, 0.3, MovingPattern.LIFTING, Color.RED, 50));
							if(enemy.reduceHP(damage)) {
								combatMenu.combatResult = CombatResult.PLAYER_WON;
								combatMenu.setOpen(false);
								return;
							}
							
							enemyTurn();
							enablePanel(true);
							
							break;
						case "run":
							for(int i = 0; i < 10; i++)
								drawPanel.playAnimation(new CombatAnimation(RUN_ANIM, Statics.frameSize.width / 2, Statics.frameSize.height / 2, true));
							
							if(new IntRange(0, 100).getRandom() > 50) {
								drawPanel.playAnimation(new TextAnimation("SUCCSESS", Statics.frameSize.width / 2, Statics.frameSize.height / 2, 0.6, MovingPattern.LIFTING, Color.GREEN, 40));
								combatMenu.combatResult = CombatResult.PLAYER_ESCAPED;
								combatMenu.setOpen(false);
								return;
								
							}else {
								drawPanel.playAnimation(new TextAnimation("MISS", Statics.frameSize.width / 2, Statics.frameSize.height / 2, 0.6, MovingPattern.LIFTING, Color.RED, 40));
								enemyTurn();
							}
							break;
					}
				}
			};
			
			for(int i = 0; i < ACTION_COUNT; i++) {
				String buttonTitle;
				
				switch(i) {
					case 0:
						buttonTitle = "attack";
						break;
					case 1:
						buttonTitle = "item";
						break;
					case 2:
						buttonTitle = "run";
						break;
					
					default:
						buttonTitle = "INVALID";
				}
				
				final RPGButton newButt = new RPGButton("combat." + buttonTitle);
				newButt.setBounds(0, i * (BUTTON_HEIGHT + 5), BUTTON_WIDTH, BUTTON_HEIGHT);
				if(buttonTitle == "run" && !isEscapeable) newButt.setEnabled(false);
				
				newButt.setActionCommand(buttonTitle);
				newButt.addActionListener(actionListener);
				
				actions[i] = newButt;
				add(actions[i]);
			}
		}
		
		public void enablePanel(final boolean enable) {
			repaint();
			for(int i = 0; i < ACTION_COUNT; i++) {
				actions[i].setEnabled(enable);
				if(i == 2 && !isEscapeable) actions[i].setEnabled(false);
			}
		}
		
		public void enemyTurn() {
			ActionControlPanel.this.drawPanel.playAnimation(new CombatAnimation(ATTACK_ANIMATION_SHEET, drawPanel.playerLoc.x, drawPanel.playerLoc.y, false));
			final int damage = enemy.getWeaponDamageRange().getRandom();
			drawPanel.playAnimation(new TextAnimation("" + damage, drawPanel.playerLoc.x + 300, drawPanel.playerLoc.y + 200, 0.3, MovingPattern.LIFTING, Color.RED, 50));
			
			if(player.reduceHP(damage)) {
				combatMenu.combatResult = CombatResult.ENEMY_WON;
				combatMenu.setOpen(false);
				return;
			}
			drawPanel.draw();
		}
	}
	
	private static class DrawPanel extends Canvas {
		private static final long serialVersionUID = -8394656885155990598L;
		
		private final BufferStrategy strat;
		
		private static int PLAYERSCALE = 7;
		private final BufferedImage playerIMG, enemyIMG;
		private final LivingEntity enemy, player;
		
		private CombatAnimation currentAnimation;
		
		public Point playerLoc, enemyLoc;
		
		public DrawPanel(final LivingEntity enemy, final CombatMenu menuToAdd) {
			setVisible(true);
			menuToAdd.addComponent(this);
			createBufferStrategy(2);
			strat = getBufferStrategy();
			
			//			Logger.debug(enemy);
			this.enemy = enemy;
			player = RPG.gameField.getPlayerController().getPlayer();
			setSize(Statics.frameSize);
			playerIMG = ImageUtility.scale(player.getSprite().getAnimation("walking/left").currentFrame(), Statics.scale * PLAYERSCALE);
			enemyIMG = ImageUtility.scale(enemy.getSprite().getAnimation("walking/right").currentFrame(), Statics.scale * PLAYERSCALE);
			
			final Dimension size = Statics.frameSize;
			playerLoc = new Point(size.width - playerIMG.getWidth() - 20, (int) (size.height * 0.3));
			enemyLoc = new Point((int) (size.width * 0.15), (int) (size.height * 0.15));
			draw();
		}
		
		public void playAnimation(final CombatAnimation animation) {
			animation.reset();
			currentAnimation = animation;
			
			double timeSinceLastFrame = 0;
			long timeLastFrameBegun = System.currentTimeMillis();
			
			while(!animation.update(timeSinceLastFrame)) {
				timeSinceLastFrame = (System.currentTimeMillis() - timeLastFrameBegun) / 1000D;
				timeLastFrameBegun = System.currentTimeMillis();
				draw();
				//				Logger.debug("[CombatMenu] >> tslf:" + timeSinceLastFrame);
			}
			currentAnimation = null;
			draw();
		}
		
		protected void draw() {
			//			Logger.debug("[ComponentMenu >> DrawPanel] >> Repaint.");
			
			final Dimension size = Statics.frameSize;
			
			final DrawingGraphics g = new DrawingGraphics(strat.getDrawGraphics());
			
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, size.width, size.height);
			g.drawImage(playerIMG, playerLoc.x, playerLoc.y, null);
			
			drawLiveBar(new Rectangle(playerLoc.x, playerLoc.y - 20, (int) (size.width * 0.2), (int) (size.height * 0.05)), player.getHP(), player.getMaxHP(), g);
			
			g.drawImage(enemyIMG, enemyLoc.x, enemyLoc.y, null);
			drawLiveBar(new Rectangle(enemyLoc.x, enemyLoc.y - 20, (int) (size.width * 0.2), (int) (size.height * 0.05)), enemy.getHP(), enemy.getMaxHP(), g);
			
			if(currentAnimation != null) currentAnimation.draw(g);
			
			strat.show();
			g.dispose();
		}
		
		public void drawLiveBar(final Rectangle size, final int hp, final int maxHP, final DrawingGraphics g) {
			g.setColor(Color.RED);
			g.fill(size);
			g.setColor(Color.GREEN);
			g.fillRect(size.x, size.y, (int) (size.width * (hp / (double) maxHP)), size.height);
			g.setColor(Color.BLACK);
			g.draw(size);
		}
	}
}
