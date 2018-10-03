package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

public class Sprite {
	private final HashMap<String, LinkedList<BufferedImage>>	animations	= new HashMap<>();
	private final String										name;
	
	public Sprite(final String name) {
		this.name = name;
	}
	
	public void loadBufferedImage(final int frameWidth, final int frameHeight, final String path) {
		
	}
	
	public void addAnimation(final String animationName, final LinkedList<BufferedImage> frames) {
		animations.put(animationName, frames);
	}
	
	public LinkedList<BufferedImage> getAnimation(final String animationName) {
		return animations.get(animationName);
	}
	
	public BufferedImage getFrame(final String animationName, final int frameInAnimation) {
		return getAnimation(animationName).get(frameInAnimation);
	}
	
	public String getName() {
		return name;
	}
}
