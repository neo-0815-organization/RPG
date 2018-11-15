package rpg.api.listener.key;

import java.util.HashMap;

public class KeyboardListenerHandler {
	private static HashMap<Integer, OnKey> keys = new HashMap<>();
	private static HashMap<Integer, KeyStatus> statuses = new HashMap<>();
	
	public static void registerKey(int keyCode, OnKey onKey) {
		keys.put(keyCode, onKey);
		statuses.put(keyCode, KeyStatus.RELEASED);
	}
	
	protected static void setStatus(int keyCode, KeyStatus status) {
		statuses.put(keyCode, status);
	}
}
