package rpg.api.listener.key;

public enum KeyState {
	
	// LOW
	RELEASED,
	
	// HIGH
	PRESSED,
	
	// FALLING_EDGE
	RELEASING,
	
	// RISING_EDGE
	PRESSING;
	
	public boolean isActive() {
		return this != RELEASED;
	}
}
