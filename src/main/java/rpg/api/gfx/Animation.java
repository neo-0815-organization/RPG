package rpg.api.gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

public class Animation {
	private final String	animationName;
	private final int		frameHeight;
	private final Rectangle	bounds;
	private Frame			currentFrame	= null;
	
	public Animation(final String animationName, final int frameHeight, final int frameWidth, final LinkedList<BufferedImage> frames) {
		this.animationName = animationName;
		this.frameHeight = frameHeight;
		
		bounds = new Rectangle(0, 0, frameWidth, frameHeight);
		loadFramesFromList(frames);
	}
	
	@SuppressWarnings("null")
	public void loadFramesFromList(final LinkedList<BufferedImage> frames) {
		final Iterator<BufferedImage> frameIterator = frames.descendingIterator();
		
		Frame frameToAdd = null,
				animationEnd = null;
		
		while(frameIterator.hasNext()) {
			frameToAdd = new Frame(frameIterator.next());
			frameToAdd.setNextFrame(currentFrame);
			
			if(animationEnd == null) animationEnd = frameToAdd;
			
			currentFrame = frameToAdd;
		}
		
		animationEnd.setNextFrame(currentFrame);
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public String getName() {
		return animationName;
	}
	
	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public BufferedImage currentFrame() {
		final BufferedImage img = currentFrame.getFrame();
		
		return img;
	}
	
	public BufferedImage nextFrame() {
		final BufferedImage img = currentFrame.getFrame();
		
		currentFrame = currentFrame.getNextFrame();
		
		return img;
	}
	
	private class Frame {
		private final BufferedImage	frame;
		private Frame				nextFrame;
		
		public Frame(final BufferedImage frame) {
			this.frame = frame;
		}
		
		public void setNextFrame(final Frame nextFrame) {
			this.nextFrame = nextFrame;
		}
		
		public Frame getNextFrame() {
			return nextFrame;
		}
		
		public BufferedImage getFrame() {
			return frame;
		}
	}
}
