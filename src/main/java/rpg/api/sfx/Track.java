package rpg.api.sfx;

import java.io.IOException;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineUnavailableException;

import rpg.api.filehandling.ResourceGetter;

public class Track {
	public static Clip clip;
	
	private final String				name;
	private final AudioInputStream		audStream;
	private static Consumer<LineEvent>	listener	= (event) -> {};
	
	static {
		try {
			clip = AudioSystem.getClip();
		} catch(final LineUnavailableException e) {
			e.printStackTrace();
		}
		
		clip.addLineListener(event -> {
			if(event.getType() == Type.STOP) {
				listener.accept(event);
				
				listener = null;
			}
		});
	}
	
	public Track(final String name) {
		audStream = ResourceGetter.getAudio("/assets/sfx/" + name);
		this.name = name;
		audStream.mark(Integer.MAX_VALUE);
	}
	
	public void start(final Runnable onTrackEnd) {
		try {
			clip.open(audStream);
			
			clip.start();
			
			listener = event -> {
				clip.close();
				
				try {
					audStream.reset();
				} catch(final IOException e) {
					e.printStackTrace();
				}
				
				onTrackEnd.run();
			};
		} catch(LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("now playing: " + this);
	}
	
	@Override
	public String toString() {
		return name.replaceAll("\\..*", "");
	}
}
