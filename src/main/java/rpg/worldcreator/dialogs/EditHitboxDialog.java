package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
		
//		JFormattedTextField rectX1 = new JFormattedTextField(NumberFormat.getNumberInstance());
//		rectX1.setBounds(100, 50, 100, 50);
//		rectanglePanel.add(rectX1);
//		
//		JFormattedTextField rectY1 = new JFormattedTextField(NumberFormat.getNumberInstance());
//		rectY1.setBounds(400, 50, 100, 50);
//		rectanglePanel.add(rectY1);
		
		PointInput rect1 = new PointInput(true, 400, 25);
		rect1.setLocation(100, 50);
		rectanglePanel.add(rect1);
		
		JFormattedTextField rectX2 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectX2.setBounds(100, 150, 100, 50);
		rectanglePanel.add(rectX2);
		
		JFormattedTextField rectY2 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectY2.setBounds(400, 150, 100, 50);
		rectanglePanel.add(rectY2);
		
		JFormattedTextField rectX3 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectX3.setBounds(100, 250, 100, 50);
		rectanglePanel.add(rectX3);
		
		JFormattedTextField rectY3 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectY3.setBounds(400, 250, 100, 50);
		rectanglePanel.add(rectY3);
		
		JFormattedTextField rectX4 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectX4.setBounds(100, 350, 100, 50);
		rectanglePanel.add(rectX4);
		
		JFormattedTextField rectY4 = new JFormattedTextField(NumberFormat.getNumberInstance());
		rectY4.setBounds(400, 350, 100, 50);
		rectanglePanel.add(rectY4);
		
		typePanels.put("Rectangle", rectanglePanel);
		
		JPanel trianglePanel = new JPanel();
		trianglePanel.setLayout(null);
		typePanels.put("Triangle", trianglePanel);
		
		JPanel circlePanel = new JPanel();
		circlePanel.setLayout(null);
		typePanels.put("Circle", circlePanel);
	}
	
	private class PointInput extends JPanel {
		private static final long serialVersionUID = 7343936080384113644L;
		
		private JFormattedTextField x,y;
		
		public PointInput(final boolean twoTuple, final int width, final int height) {
			setSize(width, height);
			
			if(twoTuple) {
				JLabel openingParenthesis = new JLabel("(");
				openingParenthesis.setBounds(0, 0, 10, height);
				add(openingParenthesis);
				
				x = new JFormattedTextField(NumberFormat.getNumberInstance());
//				x.setBounds(15, 0, 100, height);
				add(x);
				
				JLabel semicolon = new JLabel(";");
				add(semicolon);
				
				y = new JFormattedTextField(NumberFormat.getNumberInstance());
//				y.setBounds(width - 15, 0, 100, height);
				add(y);
				
				JLabel closingParenthesis = new JLabel(")");
				openingParenthesis.setBounds(width - 10, 0, 10, height);
				add(closingParenthesis);
			}else {
				x = new JFormattedTextField(NumberFormat.getNumberInstance());
				add(x);
			}
		}
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
