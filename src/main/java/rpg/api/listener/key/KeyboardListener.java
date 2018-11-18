package rpg.api.listener.key;

import java.util.HashMap;

public class KeyboardListener {
	private static HashMap<Integer, OnKey> keys = new HashMap<>();
	private static HashMap<Integer, KeyState> states = new HashMap<>();
	
	private static Thread thread = new Thread("KeyboardListenerThread") {
		
		@Override
		public void run() {
			while(!interrupted())
				states.entrySet().parallelStream().filter(entry -> entry.getValue().isActive()).forEach(entry -> keys.get(entry.getKey()).onKey(entry.getValue()));
		};
	};
	
	public static void registerKey(final int keyCode, final OnKey onKey) {
		keys.put(keyCode, onKey);
		states.put(keyCode, KeyState.RELEASED);
	}
	
	protected static void setState(final int keyCode, final KeyState state) {
		if(!keys.containsKey(keyCode)) return;
		
		states.put(keyCode, state);
	}
	
	public static void start() {
		thread.start();
	}
}
