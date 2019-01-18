package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import rpg.worldcreator.WorldCreatorFrame;
import rpg.worldcreator.WorldCreatorFrame.SpritePane;
import rpg.worldcreator.WorldCreatorHitbox;

public class EditHitboxDialog extends JDialog {
	private static final long serialVersionUID = 4801731920330519103L;
	
	private JComboBox<String> types;
	private final SpritePane pane;
	private final WorldCreatorHitbox hitbox;
	
	public EditHitboxDialog(final WorldCreatorFrame frame, final SpritePane pane) {
		super(frame, "Hitbox Tool", true);
		
		this.pane = pane;
		hitbox = pane.getHitboxCopy();
		
		setSize(600, 500);
		setLocationRelativeTo(frame);
		setLayout(new BorderLayout());
		
		initComponents();
	}
	
	private void initComponents() {
		types = new JComboBox<>(new String[] { "None", "Rectangle", "Triangle", "Circle" });
		if(!hitbox.isNull()) types.setSelectedItem(hitbox.getType());
		types.addItemListener(new ItemListener() {
			private boolean first = true;
			private String type;
			
			@Override
			public void itemStateChanged(final ItemEvent e) {
				if(!first) {
					type = (String) e.getItem();
					
					hitbox.setType(type.equals("None") ? null : type);
				}
				
				first = !first;
			}
		});
		add(types, BorderLayout.NORTH);
	}
	
	public WorldCreatorHitbox getHitbox() {
		return hitbox;
	}
}
