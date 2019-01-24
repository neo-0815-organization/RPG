package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import rpg.worldcreator.WorldCreatorFrame;
import rpg.worldcreator.WorldCreatorFrame.SpritePane;
import rpg.worldcreator.WorldCreatorHitbox;

public class EditHitboxDialog extends JDialog {
	private static final long serialVersionUID = 4801731920330519103L;
	
	private final SpritePane pane;
	private final WorldCreatorHitbox hitbox;
	private final HashMap<String, JPanel> typePanels = new HashMap<>();
	
	private JComboBox<String> types;
	private JPanel panelContainer;
	
	public EditHitboxDialog(final WorldCreatorFrame frame, final SpritePane pane) {
		super(frame, "Hitbox Tool", true);
		
		this.pane = pane;
		hitbox = pane.getHitboxCopy();
		
		initTypePanels();
		
		setSize(600, 500);
		setLocationRelativeTo(frame);
		
		setLayout(new BorderLayout());
		
		initComponents();
	}
	
	private void initTypePanels() {
		typePanels.put("None", new JPanel());
		
		JPanel rectanglePanel = new JPanel();
		rectanglePanel.setLayout(null);
		typePanels.put("Rectangle", rectanglePanel);
		
		JPanel trianglePanel = new JPanel();
		trianglePanel.setLayout(null);
		typePanels.put("Triangle", trianglePanel);
		
		JPanel circlePanel = new JPanel();
		circlePanel.setLayout(null);
		typePanels.put("Circle", circlePanel);
	}

	private void initComponents() {
		types = new JComboBox<>(getTypes());
		if(!hitbox.isNull()) types.setSelectedItem(hitbox.getType());
		types.addItemListener(new ItemListener() {
			private boolean first = true;
			private String type;
			
			@Override
			public void itemStateChanged(final ItemEvent e) {
				if(!first) {
					type = (String) e.getItem();
					
					panelContainer.removeAll();
					panelContainer.add(typePanels.get(type));
					panelContainer.revalidate();
					panelContainer.repaint();
					
					hitbox.setType(type.equals("None") ? null : type);
				}
				
				first = !first;
			}
		});
		add(types, BorderLayout.NORTH);
		
		panelContainer = new JPanel();
		panelContainer.setLayout(new BorderLayout());
		add(panelContainer, BorderLayout.CENTER);
	}
	
	public String[] getTypes() {
		String[] types = new String[typePanels.size()];
		
		types[0] = "None";
		
		int i = 1;
		for(String type : typePanels.keySet()) {
			if(!type.equals("None")) types[i] = type;
			
			i++;
		}
		
		return types;
	}
	
	public WorldCreatorHitbox getHitbox() {
		return hitbox;
	}
}
