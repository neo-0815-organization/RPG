package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;

import rpg.api.TableFileReader;

public class Sprite {
	private static final HashMap<String, Sprite> loadedSprites = new HashMap<>();
	
	private final HashMap<String, Animation>	animations	= new HashMap<>();
	private Animation							currentAnimation;
	private final String						name;
	private SpriteTheme							loadedTheme	= SpriteTheme.DEFAULT;
	
	public Sprite(final String name, final SpriteTheme theme) {
		this.name = name;
		
		loadTheme(theme);
		
		loadedSprites.put(getName(), this);
	}
	
	public void loadTheme(final SpriteTheme theme) {
		loadedTheme = theme;
		
		final Set<String> animationNames = animations.keySet();
		
		final HashMap<String, String> frameCounts = TableFileReader.read(getPath() + "/animations_frameheight.txt", ":");
		
		for(final String animationName : animationNames)
			addAnimation(animationName, Integer.valueOf(frameCounts.get(animationName)));
	}
	
	public void dumpAllAnimations() {
		currentAnimation = null;
		
		animations.clear();
	}
	
	public void dumpAnimation(final String animationName) {
		if(currentAnimation.getName() == animationName) currentAnimation = null;
		animations.remove(animationName);
	}
	
	public void addAnimation(final String animationName, final int frameHeight) throws IllegalArgumentException {
		BufferedImage animation;
		try {
			animation = ImageIO.read(getClass().getResource(getPath() + "/" + animationName + ".png"));
		} catch(final IOException ex) {
			throw new IllegalArgumentException("File '" + animationName + ".png' doesn't exist in the directory '" + getPath() + "'.");
		}
		
		final int animWidth = animation.getWidth();
		final int animHeight = animation.getHeight();
		
		if(animHeight % frameHeight != 0) throw new IllegalArgumentException("Frame height " + frameHeight + "px doesn't match animation height " + animHeight + "px.");
		
		final int frameCount = animHeight / frameHeight;
		
		final LinkedList<BufferedImage> frames = new LinkedList<>();
		
		for(int loadingFrame = 0; loadingFrame < frameCount; loadingFrame++)
			frames.add(animation.getSubimage(0, frameCount * frameHeight, animWidth, frameHeight));
		
		animations.put(animationName, new Animation(animationName, frameHeight, frames));
	}
	
	public BufferedImage getNextAnimationFrame(final String animationName) {
		if(currentAnimation.getName() == animationName) return currentAnimation.nextFrame();
		
		currentAnimation = animations.get(animationName);
		
		return currentAnimation.nextFrame();
	}
	
	public String getPath() {
		return loadedTheme.getPath() + "/" + getName();
	}
	
	public String getName() {
		return name;
	}
}
