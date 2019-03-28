package rpg.api.sfx;

import java.util.LinkedList;

import javax.sound.sampled.Clip;

public class SongQueue {
	private static final LinkedList<Song>	songs	= new LinkedList<>();
	public static final Thread				playBack;
	private static Track[]					tracks;
	
	static {
		playBack = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!songs.isEmpty()) {
					tracks = songs.getFirst().getTracks();
					
					// i is incremented at the end of the loop;
					for(int i = 0; i < tracks.length;) {
						tracks[i].start(() -> resume());
						
						if(i == 1 && songs.size() == 1)
							Track.clip.loop(Clip.LOOP_CONTINUOUSLY);
						else
							Track.clip.loop(0);
						
						synchronized(this) {
							try {
								wait();
							} catch(final InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						if(songs.size() > 1) {
							if(i == tracks.length - 1)
								break;
							else
								i++;
						} else if(i == tracks.length - 2)
							i = 1;
						else
							i++;
					}
					
					songs.remove();
				}
			}
			
			synchronized void resume() {
				notify();
			}
		}, "songPlayBackThread");
	}
	
	public static void enq(final Song s) {
		System.out.println("enq: " + s.toString());
		
		songs.addLast(s);
		
		Track.clip.loop(0);
	}
}
