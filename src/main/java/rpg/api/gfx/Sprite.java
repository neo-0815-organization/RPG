package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;

import rpg.api.filereading.RPGFileReader;

/**
 * Encapsulates multiple {@link Animation}s in one {@link Sprite}.
 *
 * @author Tim Ludwig
 */
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
	
	/**
	 * Loads the {@link SpriteTheme}.
	 *
	 * @param theme
	 *     the {@link SpriteTheme} to load
	 */
	public void loadTheme(final SpriteTheme theme) {
		loadedTheme = theme;
		
		final Set<String> animationNames = animations.keySet();
		
		final HashMap<String, String> frameCounts = RPGFileReader.readLineSplit(getPath() + "/animations_frameheight.txt", ":");
		
		for(final String animationName : animationNames)
			addAnimation(animationName, Integer.valueOf(frameCounts.get(animationName)));
	}
	
	/**
	 * Dumps all {@link Animation}s stored in this {@link Sprite}.
	 */
	public void dumpAllAnimations() {
		currentAnimation = null;
		
		animations.clear();
	}
	
	/**
	 * Dumps one {@link Animation} stored in this {@link Sprite}.
	 *
	 * @param animationName
	 *     the name of the {@link Animation} to dump
	 */
	public void dumpAnimation(final String animationName) {
		if(currentAnimation.getName() == animationName) currentAnimation = null;
		
		animations.remove(animationName);
	}
	
	/**
	 * Loads an {@link Animation}.
	 *
	 * @param animationName
	 *     the name of the {@link Animation} to load
	 * @param frameHeight
	 *     the height of all frames in the {@link Animation}
	 * @throws IllegalArgumentException
	 *     if the animation isn't found, or the frameHeight doesn't match the
	 *     height of the {@link File} found
	 */
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
			frames.add(animation.getSubimage(0, loadingFrame * frameHeight, animWidth, frameHeight));
		
		animations.put(animationName, new Animation(animationName, frameHeight, animWidth, frames));
	}
	
	/**
	 * Gets the width of the current frame.
	 *
	 * @return the width of the current frame
	 * @see Animation#getFrameWidth()
	 */
	public int getWidth() {
		return currentAnimation.getFrameWidth();
	}
	
	/**
	 * Gets the height of the current frame.
	 *
	 * @return the height of the current frame
	 * @see Animation#getFrameHeight()
	 */
	public int getHeight() {
		return currentAnimation.getFrameWidth();
	}
	
	/**
	 * Skips the current {@link Animation} to the next frame.
	 *
	 * @see Animation#nextFrame()
	 */
	public void nextFrame() {
		currentAnimation.nextFrame();
	}
	
	/**
	 * Gets the current frame.
	 *
	 * @return the current frame
	 * @see Animation#currentFrame()
	 */
	public BufferedImage getCurrentAnimationFrame() {
		return currentAnimation.currentFrame();
	}
	
	/**
	 * Sets the current {@link Animation}.
	 *
	 * @param animationName
	 *     the name of the {@link Animation} to set
	 */
	public void setAnimation(final String animationName) {
		if(currentAnimation.getName() != animationName) currentAnimation = animations.get(animationName);
	}
	
	/**
	 * Gets the path corresponding to this {@link Sprite} and {@link SpriteTheme}.
	 *
	 * @return the path corresponding to the animations in the currently loaded
	 * {@link SpriteTheme}
	 */
	public String getPath() {
		return loadedTheme.getPath() + "/" + getName();
	}
	
	/**
	 * Gets the name of this {@link Sprite}.
	 *
	 * @return the name of this {@link Sprite}
	 */
	public String getName() {
		return name;
	}
}
