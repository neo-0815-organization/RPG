package rpg.api.listener.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardSensor extends KeyAdapter {
	
	@Override
	public void keyPressed(final KeyEvent e) {
		KeyboardListener.setState(e.getKeyCode(), KeyState.PRESSING);
	}
	
	@Override
	public void keyReleased(final KeyEvent e) {
		KeyboardListener.setState(e.getKeyCode(), KeyState.RELEASING);
	}
}
