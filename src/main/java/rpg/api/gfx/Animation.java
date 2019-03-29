package rpg.api.gfx;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Encapsulates multiple {@link Frame}s in one instance.
 *
 * @author Tim Ludwig
 */
public class Animation {
	private final String animationName;
	private final int frameHeight, frameWidth;
	private Frame currentFrame = null;
	
	/**
	 * Constructs a new {@link Animation}.
	 * 
	 * @param animationName the name of the new {@link Animation}
	 * @param frameHeight the height of each frame in the new {@link Animation} in pixels
	 * @param frameWidth the width of each frame in the new {@link Animation} in pixels
	 * @param frames the {@link Frame}s in the new {@link Animation}
	 * @param loop if loop is false the new {@link Animation} will stop playing upon reaching the last {@link Frame} 
	 */
	public Animation(final String animationName, final int frameHeight, final int frameWidth, final LinkedList<BufferedImage> frames, final boolean loop) {
		this.animationName = animationName;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		
		loadFramesFromList(frames, loop);
	}
	
	/**
	 * Loads the {@link BufferedImage}s in the {@link LinkedList} 'frames' into
	 * this {@link Animation}.
	 *
	 * @param frames
	 *            the {@link LinkedList} to load
	 * @param loop
	 *            the {@link Animation} should be able to loop
	 */
	public void loadFramesFromList(final LinkedList<BufferedImage> frames, final boolean loop) {
		final Iterator<BufferedImage> frameIterator = frames.descendingIterator();
		
		Frame frameToAdd = null, animationEnd = null;
		while(frameIterator.hasNext()) {
			frameToAdd = new Frame(frameIterator.next());
			frameToAdd.setNextFrame(currentFrame);
			
			if(animationEnd == null) animationEnd = frameToAdd;
			
			currentFrame = frameToAdd;
		}
		
		if(loop) animationEnd.setNextFrame(frameToAdd);
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
	 * Represents one frame in an {@link Animation} used to chain multiple
	 * frames together.
	 *
	 * @author Tim Ludwig
	 */
	private class Frame {
		private final BufferedImage frame;
		private Frame nextFrame;
		
		public Frame(final BufferedImage frame) {
			this.frame = frame;
		}
		
		/**
		 * Changes the next {@link Frame} in the chain to a new {@link Frame}.
		 *
		 * @param nextFrame
		 *            the next {@link Frame} to change to
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
			return nextFrame == null ? this : nextFrame;
		}
		
		/**
		 * Gets the {@link BufferedImage} corresponding to this frame.
		 *
		 * @return the {@link BufferedImage} corresponding to this frame
		 */
		public BufferedImage getFrame() {
			return frame;
		}
	}
}
