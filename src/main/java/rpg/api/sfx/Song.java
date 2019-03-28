package rpg.api.sfx;

import java.util.Arrays;

public class Song {
	public static final Song TETRIS = new Song("Tetris");
	
	private final String	name;
	private final Track[]	tracks;
	
	public Song(final String name) {
		this.name = name;
		tracks = new Track[] {new Track(name + "_intro.wav"), new Track(name + "_loop.wav"), new Track(name + "_outro.wav")};
	}
	
	@Override
	public String toString() {
		return name + "_" + Arrays.toString(tracks).replaceAll(name + "_", "").replaceAll("\\..*", "");
	}
	
	public Track[] getTracks() {
		return tracks;
	}
}
