package rpg.api.listener.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyboardListenerHandler.setStatus(e.getKeyCode(), KeyStatus.PRESSING);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyboardListenerHandler.setStatus(e.getKeyCode(), KeyStatus.RELEASING);
	}
}