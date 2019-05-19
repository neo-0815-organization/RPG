package rpg.api.listener.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyboardSensor extends KeyAdapter {
	private static final byte PRESSED = 0, RELEASED = 1;
	private HashMap<Integer, Byte> keyAction = new HashMap<>();
	
	@Override
	public void keyPressed(final KeyEvent e) {
		if(keyAction.get(e.getKeyCode()) == null || keyAction.get(e.getKeyCode()) != PRESSED) {
			KeyboardListener.setState(e.getKeyCode(), KeyState.PRESSING);
			
			keyAction.put(e.getKeyCode(), PRESSED);
		}
	}
	
	@Override
	public void keyReleased(final KeyEvent e) {
		if(keyAction.get(e.getKeyCode()) == null || keyAction.get(e.getKeyCode()) != RELEASED) {
			KeyboardListener.setState(e.getKeyCode(), KeyState.RELEASING);
			
			keyAction.put(e.getKeyCode(), RELEASED);
		}
	}
}
