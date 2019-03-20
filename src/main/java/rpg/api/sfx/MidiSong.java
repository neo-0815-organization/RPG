package rpg.api.sfx;

import javax.sound.midi.Sequence;

public class MidiSong {
	private final Sequence sequence;
	
	private final float	tempoBPM;
	private final long	loopStart, loopEnd;
	
	public MidiSong(final Sequence sequence, final float tempoBPM, final long loopStart, final long loopEnd) {
		this.sequence = sequence;
		this.tempoBPM = tempoBPM;
		this.loopStart = loopStart;
		this.loopEnd = loopEnd;
	}
	
	public MidiSong(final Sequence sequence, final float tempoBPM, final long loopStart) {
		this(sequence, tempoBPM, loopStart, sequence.getTickLength());
	}
	
	public MidiSong(final Sequence sequence, final float tempoBPM) {
		this(sequence, tempoBPM, 0);
	}
	
	public Sequence getSequence() {
		return sequence;
	}
	
	public float getTempoBPM() {
		return tempoBPM;
	}
	
	public long getLoopStart() {
		return loopStart;
	}
	
	public long getLoopEnd() {
		return loopEnd;
	}
}
