package rpg.api.gfx.menus.combatMenu;

import java.awt.image.BufferedImage;

import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.ImageUtility;

/**
 * 
 * @author Erik
 *
 */
public class CombatAnimationSheet {
	private static final String COMBAT_ANIMATION_DIR = "/assets/textures/combatAnimations/";
	BufferedImage[] images;
	private double timePerFrame;
	
	/**Did sb read this?
	 * 
	 * @param name (ex: attack.png)
	 * @param imageCount image count the animation contains
	 * @param timePerFrame the time each frame appears on the screen
	 * @param scale insta scale the animation
	 */
	public CombatAnimationSheet(String name, int imageCount, double timePerFrame, double scale) {
		this.timePerFrame = timePerFrame;
		
		BufferedImage allSprites = ResourceGetter.getImage(COMBAT_ANIMATION_DIR + name);
		
		if (scale != 1) 
			allSprites = ImageUtility.scale(allSprites, scale);
		
		int imageWidth = allSprites.getWidth() / imageCount;
		
		images = new BufferedImage[imageCount];
		
		for (int i = 0; i < imageCount; i++) {
			images[i] = allSprites.getSubimage(i * imageWidth, 0, imageWidth, allSprites.getHeight());
		}
	}
	
	public CombatAnimationSheet(String name, int imageCount, double timePerFrame) {
		this(name, imageCount, timePerFrame, 1);
	}

	
	public BufferedImage getFrame(int frame) {
		return images[frame];
	}
	
	public int getFrameCount() {
		return images.length;
	}
	
	public double getTimePerFrame() {
		return timePerFrame;
	}

}
