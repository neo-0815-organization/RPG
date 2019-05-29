package rpg.api.listener.key;

import java.util.HashMap;

/**
 * @author Neo Hornberger, Tim Ludwig
 */
public class KeyboardListener {
	private static HashMap<Integer, OnKey>			keys	= new HashMap<>();
	public static final HashMap<Integer, KeyState>	states	= new HashMap<>();
	
//	private static Thread thread = new Thread("KeyboardListenerThread") {
//
//		@Override
//		public void run() {
//			while(!interrupted())
//				updateKeys();
//		};
//	};
	
	public static synchronized void updateKeys() {
		states.entrySet().parallelStream().filter(entry -> entry.getValue().isActive()).forEach(entry -> {
			keys.get(entry.getKey()).onKey(entry.getValue());
			
			setState(entry.getKey(), entry.getValue().next());
		});
	}
	
	public static synchronized void registerKey(final int keyCode, final OnKey onKey) {
		keys.put(keyCode, onKey);
		states.put(keyCode, KeyState.RELEASED);
	}
	
	protected static void setState(final int keyCode, final KeyState state) {
		if(!keys.containsKey(keyCode)) return;
		
		states.put(keyCode, state);
	}
	
	@Deprecated
	public static void start() {
//		thread.start();
	}
}
