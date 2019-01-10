package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import rpg.worldcreator.WorldCreatorFrame;

public class EditHitboxDialog extends JDialog {
	private static final long serialVersionUID = 4801731920330519103L;
	
	private JComboBox<String> types;
	
	public EditHitboxDialog(final WorldCreatorFrame frame) {
		super(frame, "Hitbox Tool", true);
		
		setSize(600, 500);
		setLocationRelativeTo(frame);
		setLayout(new BorderLayout());
		
		initComponents();
	}

	private void initComponents() {
		types = new JComboBox<>(new String[] {"Rectangle", "Triangle", "Circle"});
		add(types, BorderLayout.NORTH);
	}
}
