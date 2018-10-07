package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

public class Animation {
	private final String	animationName;
	private Frame			currentFrame	= null;
	
	public Animation(final String animationName, final LinkedList<BufferedImage> frames) {
		this.animationName = animationName;
		
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
	
	public String getName() {
		return animationName;
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
