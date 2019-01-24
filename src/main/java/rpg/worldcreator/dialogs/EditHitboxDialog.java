package rpg.worldcreator.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import rpg.api.gfx.ImageUtility;
import rpg.worldcreator.WorldCreatorFrame;
import rpg.worldcreator.WorldCreatorFrame.SpritePane;
import rpg.worldcreator.WorldCreatorHitbox;

public class EditHitboxDialog extends JDialog {
	private static final long serialVersionUID = 4801731920330519103L;
	
	private final SpritePane pane;
	private final WorldCreatorHitbox hitbox;
	private final HashMap<String, JPanel> typePanels = new HashMap<>();
	
	private JComboBox<String> types;
	private JPanel panelContainer, previewHolder;
	
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
		
		// rectangular panel
		final JPanel rectanglePanel = new JPanel();
		rectanglePanel.setLayout(null);
		
		final PointInput rect1 = new PointInput(true, 250, 25);
		rect1.setLocation(100, 100);
		rectanglePanel.add(rect1);
		
		final PointInput rect2 = new PointInput(true, 250, 25);
		rect2.setLocation(100, 150);
		rectanglePanel.add(rect2);
		
		final PointInput rect3 = new PointInput(true, 250, 25);
		rect3.setLocation(100, 200);
		rectanglePanel.add(rect3);
		
		final PointInput rect4 = new PointInput(true, 250, 25);
		rect4.setLocation(100, 250);
		rectanglePanel.add(rect4);
		
		typePanels.put("Rectangle", rectanglePanel);
		
		// triangular panel
		final JPanel trianglePanel = new JPanel();
		trianglePanel.setLayout(null);
		
		final PointInput tri1 = new PointInput(true, 250, 25);
		tri1.setLocation(100, 100);
		trianglePanel.add(tri1);
		
		final PointInput tri2 = new PointInput(true, 250, 25);
		tri2.setLocation(100, 150);
		trianglePanel.add(tri2);
		
		final PointInput tri3 = new PointInput(true, 250, 25);
		tri3.setLocation(100, 200);
		trianglePanel.add(tri3);
		
		typePanels.put("Triangle", trianglePanel);
		
		// circular panel
		final JPanel circlePanel = new JPanel();
		circlePanel.setLayout(null);
		
		final PointInput cir1 = new PointInput(true, 250, 25);
		cir1.setLocation(100, 100);
		circlePanel.add(cir1);
		
		final PointInput cir2 = new PointInput(false, 250, 25);
		cir2.setLocation(100, 150);
		circlePanel.add(cir2);
		
		typePanels.put("Circle", circlePanel);
	}
	
	private class PointInput extends JPanel {
		private static final long serialVersionUID = 7343936080384113644L;
		
		private JFormattedTextField x, y;
		
		public PointInput(final boolean twoTuple, final int width, final int height) {
			setSize(width, height);
			setLayout(null);
			
			final int textFieldWidth = (width - 45) / 2;
			
			if(twoTuple) {
				final JLabel openingParenthesis = new JLabel("(");
				openingParenthesis.setBounds(0, 0, 10, height);
				add(openingParenthesis);
				
				x = new JFormattedTextField(NumberFormat.getNumberInstance());
				x.setBounds(15, 0, textFieldWidth, height);
				add(x);
				
				final JLabel semicolon = new JLabel(";");
				semicolon.setBounds(20 + textFieldWidth, 0, 10, height);
				add(semicolon);
				
				y = new JFormattedTextField(NumberFormat.getNumberInstance());
				y.setBounds(width - 15 - textFieldWidth, 0, textFieldWidth, height);
				add(y);
				
				final JLabel closingParenthesis = new JLabel(")");
				closingParenthesis.setBounds(width - 10, 0, 10, height);
				add(closingParenthesis);
			}else {
				x = new JFormattedTextField(NumberFormat.getNumberInstance());
				x.setBounds(width / 2 - textFieldWidth / 2, 0, textFieldWidth, height);
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
		
		final JPanel previewPanel = new JPanel() {
			private static final long serialVersionUID = 3649405991511062410L;
			
			@Override
			protected void paintComponent(final Graphics g) {
				super.paintComponent(g);
				
				int newSize = getHeight();
				if(newSize > getWidth()) newSize = getWidth();
				
				newSize -= 10;
				
				g.drawImage(ImageUtility.scale(pane.getImages(), newSize, newSize), 5, 5, null);
				hitbox.draw(g);
				
				g.dispose();
			}
		};
		
		previewHolder = new JPanel();
		previewHolder.setLayout(new BorderLayout());
		previewHolder.setBorder(new LineBorder(Color.LIGHT_GRAY));
		previewHolder.setPreferredSize(new Dimension(150, 0));
		previewHolder.add(previewPanel);
		add(previewHolder, BorderLayout.WEST);
	}
	
	public String[] getTypes() {
		final String[] types = new String[typePanels.size()];
		
		types[0] = "None";
		
		int i = 1;
		for(final String type : typePanels.keySet()) {
			if(!type.equals("None")) types[i] = type;
			
			i++;
		}
		
		return types;
	}
	
	public WorldCreatorHitbox getHitbox() {
		return hitbox;
	}
}
