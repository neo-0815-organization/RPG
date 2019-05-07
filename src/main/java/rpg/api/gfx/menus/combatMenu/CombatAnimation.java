package rpg.api.gfx.menus.combatMenu;

import rpg.api.gfx.DrawingGraphics;
import rpg.api.gfx.IDrawable;

public class CombatAnimation implements IDrawable {
	
	private CombatAnimationSheet sheet;
	private double delay;
	private int frame, offsetX, offsetY;
	
	/**
	 * Creates a new CombatAnimation
	 * 
	 * @param sheet
	 *            The Animation
	 * @param x
	 *            Draw-X of the Animation
	 * @param y
	 *            Draw-Y of the Animation
	 * @param midHandle
	 *            wether the origin of the animation should be in the center
	 */
	public CombatAnimation(final CombatAnimationSheet sheet, final int x, final int y, final boolean midHandle) {
		this.sheet = sheet;
		offsetX = x;
		offsetY = y;
		
		if(midHandle) {
			offsetX -= sheet.getFrame(0).getWidth() / 2;
			offsetY -= sheet.getFrame(0).getHeight() / 2;
		}
	}
	
	protected CombatAnimation() {
		
	}
	
	/**
	 * Updates the frame of the CombatAnimation. Returns True if the Animation
	 * has looped once.
	 * 
	 * @param timeSinceLastFrame
	 * @return
	 */
	public boolean update(final double timeSinceLastFrame) {
		delay += timeSinceLastFrame;
		
		if(delay >= sheet.getTimePerFrame()) {
			delay -= sheet.getTimePerFrame();
			
			return nextFrame();
		}
		
		return false;
	}
	
	public boolean nextFrame() {
		if(++frame >= sheet.getFrameCount()) {
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
	
	@Override
	public void draw(final DrawingGraphics g) {
		g.drawImage(sheet.getFrame(frame), offsetX, offsetY, null);
	}
}
