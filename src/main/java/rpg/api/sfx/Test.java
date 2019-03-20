package rpg.api.sfx;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import rpg.api.filereading.ResourceGetter;

public class Test {
	public static void main(final String[] args) throws InvalidMidiDataException, IOException, MidiUnavailableException, InterruptedException {
		final MidiPlayer player = new MidiPlayer();
		final MidiSong song = new MidiSong(ResourceGetter.getSequence("/assets/sfx/Tetris.mid"), 150);
		
		player.load(song);
		player.setLooping(true);
		
		Thread.sleep(500);
		
		player.play();
		
//		playSound();
	}
	
	public static void playSound() {
		try {
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ResourceGetter.getResource("/assets/sfx/Tetris.wav"));
			final Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(final Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
}
