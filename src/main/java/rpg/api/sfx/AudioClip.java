package rpg.api.sfx;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import rpg.api.filereading.ResourceGetter;

public class AudioClip {
	private final AudioInputStream	audStream;
	private final long				frameLength;
	
	public AudioClip(final String name) {
		audStream = ResourceGetter.getAudio(name);
		frameLength = audStream.getFrameLength();
	}
	
	public void play() throws LineUnavailableException {
		play(AudioSystem.getClip());
	}
	
	public void play(final Clip clip) {
		try {
			clip.open(audStream);
			
			clip.start();
		} catch(LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
}
