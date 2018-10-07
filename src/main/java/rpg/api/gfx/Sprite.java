package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Sprite {
	private static final HashMap<String, Sprite> loadedSprites = new HashMap<>();
	
	private final HashMap<String, LinkedList<BufferedImage>>	animations	= new HashMap<>();
	private final String										name;
	private SpriteTheme											loadedTheme	= SpriteTheme.DEFAULT;
	
	public Sprite(final String name, final SpriteTheme theme) {
		this.name = name;
		loadedTheme = theme;
		
		Sprite.loadedSprites.put(getName(), this);
	}
	
	public void loadTheme(final SpriteTheme theme) {
		loadedTheme = theme;
	}
	
	public void dumpAllAnimations() {
		animations.clear();
	}
	
	public void dumpAnimation(final String animationName) {
		animations.remove(animationName);
	}
	
	public void addAnimation(final String animationName, final int frameHeight) throws IllegalArgumentException {
		BufferedImage animation;
		try {
			animation = ImageIO.read(getClass().getResource(getPath() + "/" + animationName + ".png"));
		} catch(final IOException ex) {
			throw new IllegalArgumentException("File " + animationName + ".png doesn't exist in the directory " + getPath());
		}
		
		final int animWidth = animation.getWidth();
		final int animHeight = animation.getHeight();
		
		if(animHeight % frameHeight != 0) throw new IllegalArgumentException("Frame height " + frameHeight + "px doesn't match animation height " + animHeight + "px.");
		
		final int frameCount = animHeight / frameHeight;
		
		final LinkedList<BufferedImage> frames = new LinkedList<>();
		
		for(int loadingFrame = 0; loadingFrame < frameCount; loadingFrame++)
			frames.add(animation.getSubimage(0, frameCount * frameHeight, animWidth, frameHeight));
		
		animations.put(animationName, frames);
	}
	
	public LinkedList<BufferedImage> getAnimation(final String animationName) {
		return animations.get(animationName);
	}
	
	public BufferedImage getAnimationFrame(final String animationName, final int frameInAnimation) {
		return getAnimation(animationName).get(frameInAnimation);
	}
	
	public String getPath() {
		return loadedTheme.getPath() + "/" + getName();
	}
	
	public String getName() {
		return name;
	}
}
