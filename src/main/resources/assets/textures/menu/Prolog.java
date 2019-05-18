package assets.textures.menu;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import rpg.Statics;
import rpg.api.gfx.framework.Menu;
import rpg.api.gfx.framework.RPGButton;

public class Prolog extends Menu {
	private static final long serialVersionUID = -3906467387005786401L;
	
	int speed;
	String txt = "Prolog:\r\n" + 
			"Flug Nr. 204, Richtung Bahamas, 17:00 Uhr. Eine Durchsage ertönt durch \n die Lautsprecher des Airbus A380: „Sehr geehrte Passagiere, hier spricht Ihr Pilot, \n wir sind gerade über dem Bermudadreieck und es gibt einige Turbulenzen. \nWir bitten Sie daher sich zu Ihrem Sitzplatz zu begeben, \ndiesen in Ausgangsposition zu bringen und Ihren Sicherheitsgurt zu \nschließen. Es besteht kein Grund zur Panik, bitte bewahren Sie Ruhe. Ihr Kapitän Lou van da Louft.“ \n Die Passagiere laufen zu ihren Plätzen und schnallen sich an. Einer von ihnen bist DU! \nKurze Zeit später fangen die erwarteten Turbulenzen an.  Du merkst, wie das Flugzeug anfängt zu beben und mit ihm rappelst\n du in deinem Sitz herum. Dir wird klar, dass dies stärker als gewöhnlich ist. Als plötzlich \ndie Sauerstoffmasken aus der Decke runter kommen, bricht die Absolut totale Panik aus. Der\n Flugbegleiterin Sandra Saft fliegt der Kognak den sie dir gerade servieren wollte\n vom Tablett und spritzt durch die ganze Kabine. Und Buff (optional auch Peng, Knall,\n Bumms oder Rumms). Du siehst ein grelles Licht und alle Lichter gehen aus. \r\n" + 
			"";
	public JTextArea label;
	private double timeSinceLastFrame, allOverTime, allOverTimeToReach, y;
	private long timeLastFrameBegun;
	
	public Prolog(double scrollTime) {
		allOverTimeToReach = scrollTime;
		
		label = new JTextArea(txt);
		label.setBounds(600, Statics.frameSize.height, 500, 0);
		y = label.getY();
		
		addComponent(label);
		
		final char widestChar = 'W';
		int charWidth = menu.getFontMetrics(label.getFont()).stringWidth(widestChar+"");
		int lines = (int) (txt.length() / (label.getWidth() / (double)charWidth)) + 1;
		
		{
			int openIndex = 0;
			while((openIndex = txt.indexOf("\n", openIndex)) != -1) {
				openIndex++;
				lines++;
			}
		}
		int height = (int) (lines + Statics.frameSize.height * 1.5);
		speed = (int) (height / scrollTime);
		label.setSize(500, lines * label.getFont().getSize());
//		setBackground(RPGButton.BUTTON_TEMPLATE);
		timeLastFrameBegun = System.currentTimeMillis();

	}

	@Override
	protected void updateMenu() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {;}
		
		timeSinceLastFrame = (System.currentTimeMillis() - timeLastFrameBegun) / 1000D;
		timeLastFrameBegun = System.currentTimeMillis();
		
		y -= speed * timeSinceLastFrame;
		System.out.println(y);
		label.setLocation(label.getX(), (int) (y));

		allOverTime += timeSinceLastFrame;
		menu.repaint();
		if (allOverTime > allOverTimeToReach)setOpen(false);
	}
}
