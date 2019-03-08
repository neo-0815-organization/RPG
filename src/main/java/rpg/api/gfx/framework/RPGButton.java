package rpg.api.gfx.framework;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import rpg.api.filehandling.ResourceGetter;
import rpg.api.gfx.ImageUtility;
import rpg.api.localization.StringLocalizer;

/**
 * RPGButton is a game-specific JButton
 *
 */
public class RPGButton extends JButton {
	private static final long serialVersionUID = 6887580054889086469L;
	private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 24);
	
	public static final BufferedImage BUTTON_TEMPLATE = ResourceGetter.getImage("/assets/textures/menu/button.png");
	
	private BufferedImage image, focusImage;
	private final String title;
	
	public RPGButton() {
		this(null, DEFAULT_FONT, null);
	}
	
	public RPGButton(final String title) {
		this(title, BUTTON_TEMPLATE);
	}
	
	/**
	 * A button with a background image
	 * 
	 * @param image
	 */
	public RPGButton(final BufferedImage image) {
		this(null, image);
	}
	
	/**
	 * A button with a background image and a title
	 * 
	 * @param title
	 * @param image
	 */
	public RPGButton(final String title, final BufferedImage image) {
		this(title, DEFAULT_FONT, image);
	}
	
	/**
	 * A button with a background image and a title in a specific font
	 * 
	 * @param title
	 * @param font
	 * @param image
	 */
	public RPGButton(final String title, final Font font, final BufferedImage image) {
		super(title == null ? null : StringLocalizer.localize(title + ".name"));
		
		this.image = image;
		this.title = title;
		
		setOpaque(false);
		setContentAreaFilled(false);
		setFont(font);
		setVerticalTextPosition(JButton.CENTER);
		setHorizontalTextPosition(JButton.CENTER);
	}
	
	/**
	 * Makes the border of the button invisible
	 */
	public void disableBorder() {
		setBorder(null);
	}
	
	public void setBackgroundImage(final BufferedImage img) {
		image = img;
		
		setBounds(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void setBounds(final int x, final int y, final int width, final int height) {
		super.setBounds(x, y, width, height);
		
		setIcon(new ImageIcon(ImageUtility.scale(image, width, height)));
	}
	
	public void updateTitle() {
		setText(StringLocalizer.localize(title + ".name"));
	}
	
	/**
	 * Image when mouse hovers over the Button
	 * 
	 * @param focus
	 */
	
	public void addFocusImage(final BufferedImage focus) {
		focusImage = focus;
		
		addMouseListener(new MouseAdapter() {
			private BufferedImage temp;
			
			@Override
			public void mouseEntered(final MouseEvent e) {
				temp = focusImage;
				focusImage = image;
				
				setBackgroundImage(temp);
			}
			
			@Override
			public void mouseExited(final MouseEvent e) {
				temp = focusImage;
				focusImage = image;
				
				setBackgroundImage(temp);
			}
		});
	}
}
