package rpg.api.sfx;

import java.util.LinkedList;

import javax.sound.sampled.Clip;

public class SongQueue {
	private static final LinkedList<Song>	songs	= new LinkedList<>();
	public static final Thread				playBack;
	private static Track[]					tracks;
	private static boolean					stop;
	
	static {
		playBack = new Thread(new Runnable() {
			@Override
			public void run() {
				stop = false;
				
				while(!songs.isEmpty() && !stop) {
					tracks = songs.getFirst().getTracks();
					
					// i is incremented at the end of the loop;
					for(int i = 0; i < tracks.length;) {
						tracks[i].start(() -> resume());
						
						Track.clip.loop(i == 1 && songs.size() == 1 && !stop ? Clip.LOOP_CONTINUOUSLY : 0);
						
						synchronized(this) {
							try {
								wait();
							} catch(final InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						if(i != 1 || stop)
							i++;
						else if(songs.size() > 1 && i == 2)
							break;
					}
					
					songs.remove();
				}
			}
			
			synchronized void resume() {
				notify();
			}
		}, "songPlayBackThread");
	}
	
	public static void stop() {
		System.out.println("Stopping");
		
		stop = true;
		Track.clip.loop(0);
	}
	
	public static void enq(final Song s) {
		System.out.println("enq: " + s.toString());
		
		songs.addLast(s);
		
		Track.clip.loop(0);
	}
}
