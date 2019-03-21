package rpg.api.sfx;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Song {
	private final AudioClip loop, intro, outro;
	
	public Song(final String name) {
		loop = new AudioClip(name + "_loop.wav");
		intro = new AudioClip(name + "_intro.wav");
		outro = new AudioClip(name + "_outro.wav");
	}
	
	public void start() throws LineUnavailableException {
		start(AudioSystem.getClip());
	}
	
	public void start(final Clip clip) {
		intro.play(clip);
	}
}
