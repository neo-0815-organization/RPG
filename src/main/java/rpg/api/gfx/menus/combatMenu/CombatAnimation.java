package rpg.api.gfx.menus.combatMenu;

import java.awt.Graphics2D;

public final class CombatAnimation {
	
	private CombatAnimationSheet sheet;
	private double delay;
	private int frame, offsetX, offsetY;
	
	/**Creates a new CombatAnimation
	 * 
	 * @param sheet The Animation
	 * @param x Draw-X of the Animation
	 * @param y Draw-Y of the Animation
	 * @param midHandle wether the origin of the animation should be in the center
	 */
	public CombatAnimation(CombatAnimationSheet sheet, int x, int y, boolean midHandle) {
		this.sheet = sheet;
		offsetX = x;
		offsetY = y;
		
		if (midHandle) {
			offsetX += sheet.getFrame(0).getWidth() / 2;
			offsetY += sheet.getFrame(0).getHeight() / 2;
		}
	}
	
	/**Updates the frame of the CombatAnimation.
	 * Returns True if the Animation has looped once.
	 * 
	 * @param timeSinceLastFrame
	 * @return
	 */
	public boolean update(double timeSinceLastFrame) {
		delay += timeSinceLastFrame;
		if (delay >= sheet.getTimePerFrame()) {
			delay -= sheet.getTimePerFrame();
			return nextFrame();
		}
		return false;
	}
	
	public boolean nextFrame() {
		if (++frame >= sheet.getFrameCount()) {
			frame = 0;
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the frame (and CurrentDelay) of the animation to zero.
	 */
	public void reset() {
		delay = 0;
		frame = 0;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(sheet.getFrame(frame), offsetX, offsetY, null);
	}
	
}
