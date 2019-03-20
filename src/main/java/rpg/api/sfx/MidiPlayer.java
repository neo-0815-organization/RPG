package rpg.api.sfx;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MidiPlayer implements MetaEventListener {
	private Sequencer sequencer;
	
	private boolean	loop;
	private boolean	paused;
	
	public MidiPlayer() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
		} catch(final MidiUnavailableException e) {
			sequencer = null;
			
			e.printStackTrace();
		}
	}
	
	public void load(final MidiSong song) {
		if(sequencer != null && song != null && sequencer.isOpen())
			try {
				sequencer.setSequence(song.getSequence());
				
				sequencer.setTempoInBPM(song.getTempoBPM());
				
				sequencer.setLoopStartPoint(song.getLoopStart());
				sequencer.setLoopEndPoint(song.getLoopEnd());
			} catch(final InvalidMidiDataException e) {
				e.printStackTrace();
			}
	}
	
	public void play() {
		sequencer.start();
	}
	
	public void setLooping(final boolean loop) {
		this.loop = loop;
		if(loop)
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	}
	
	@Override
	public void meta(final MetaMessage meta) {
		
	}
	
	public void stop() {
		if(sequencer != null && sequencer.isOpen()) {
			sequencer.stop();
			sequencer.setMicrosecondPosition(0);
		}
	}
	
	public void close() {
		if(sequencer != null && sequencer.isOpen())
			sequencer.close();
	}
	
	public Sequencer getSequencer() {
		return sequencer;
	}
	
	public void setPaused(final boolean paused) {
		if(this.paused != paused && sequencer != null && sequencer.isOpen()) {
			this.paused = paused;
			if(paused)
				sequencer.stop();
			else
				sequencer.start();
		}
	}
	
	public boolean isPaused() {
		return paused;
	}
}
