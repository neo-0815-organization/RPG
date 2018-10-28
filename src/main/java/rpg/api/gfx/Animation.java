package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Encapsulates multiple {@link Frame}s in one class.
 *
 * @author Tim Ludwig
 */
public class Animation {
	private final String	animationName;
	private final int		frameHeight,
			frameWidth;
	private Frame			currentFrame	= null;
	
	public Animation(final String animationName, final int frameHeight, final int frameWidth, final LinkedList<BufferedImage> frames) {
		this.animationName = animationName;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		
		loadFramesFromList(frames);
	}
	
	/**
	 * Loads the {@link BufferedImage}s in the {@link LinkedList} 'frames' into this
	 * {@link Animation}.
	 *
	 * @param frames
	 *     the {@link LinkedList} to load
	 */
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
		
		animationEnd.setNextFrame(frameToAdd);
	}
	
	/**
	 * Gets the height of every frame in this {@link Animation}.
	 *
	 * @return the frame height
	 */
	public int getFrameHeight() {
		return frameHeight;
	}
	
	/**
	 * Gets the width of every frame in this {@link Animation}.
	 *
	 * @return the frame width
	 */
	public int getFrameWidth() {
		return frameWidth;
	}
	
	/**
	 * Gets the name of this {@link Animation}.
	 *
	 * @return the name
	 */
	public String getName() {
		return animationName;
	}
	
	/**
	 * Gets the current frame of this {@link Animation}.
	 *
	 * @return the current frame
	 */
	public BufferedImage currentFrame() {
		return currentFrame.getFrame();
	}
	
	/**
	 * Skips to the next {@link Frame} of this {@link Animation}.
	 */
	public void nextFrame() {
		currentFrame = currentFrame.getNextFrame();
	}
	
	/**
	 * Represents one frame in an {@link Animation}
	 * used to chain multiple frames together.
	 *
	 * @author Tim Ludwig
	 */
	private class Frame {
		private final BufferedImage	frame;
		private Frame				nextFrame;
		
		public Frame(final BufferedImage frame) {
			this.frame = frame;
		}
		
		/**
		 * Sets the next {@link Frame} in the chain.
		 *
		 * @param nextFrame
		 *     the next {@link Frame}
		 */
		public void setNextFrame(final Frame nextFrame) {
			this.nextFrame = nextFrame;
		}
		
		/**
		 * Gets the next {@link Frame} in the chain.
		 *
		 * @return the next {@link Frame}
		 */
		public Frame getNextFrame() {
			return nextFrame;
		}
		
		/**
		 * Gets the {@link BufferedImage} representing this frame in the
		 * {@link Animation}.
		 *
		 * @return the {@link BufferedImage} representing this frame
		 */
		public BufferedImage getFrame() {
			return frame;
		}
	}
}
