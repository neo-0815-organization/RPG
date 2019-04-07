package rpg.worldcreator.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;

import rpg.worldcreator.WorldCreatorFrame;

public class NewMapDialog extends JDialog {
	private static final long serialVersionUID = 2831735648267384483L;
	
	private JFormattedTextField width, height;
	private Dimension dimension;
	private final ActionListener buttonActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			switch(e.getActionCommand()) {
				case "cancel":
					dimension = null;
					
					setVisible(false);
					break;
				case "create":
					try {
						dimension = new Dimension(Integer.valueOf(width.getText()), Integer.valueOf(height.getText()));
					}catch(final NumberFormatException ex) {}
					
					setVisible(false);
					break;
			}
		}
	};
	
	public NewMapDialog(final WorldCreatorFrame frame) {
		super(frame, "New", true);
		
		setSize(290, 160);
		setLocationRelativeTo(frame);
		setResizable(false);
		setLayout(null);
		
		initComponents();
	}
	
	private void initComponents() {
		final JTextPane widthLabel = new JTextPane();
		widthLabel.setEditable(false);
		widthLabel.setFocusable(false);
		widthLabel.setEditorKit(new HTMLEditorKit());
		widthLabel.setOpaque(false);
		widthLabel.setSelectionColor(new Color(0, 0, 0, 0));
		widthLabel.setText("Width (in tiles):");
		widthLabel.setBounds(10, 8, 100, 25);
		add(widthLabel);
		
		width = new JFormattedTextField(NumberFormat.getIntegerInstance());
		width.setBounds(110, 10, 150, 20);
		width.setBorder(new LineBorder(Color.GRAY, 1));
		add(width);
		
		final JTextPane heightLabel = new JTextPane();
		heightLabel.setEditable(false);
		heightLabel.setFocusable(false);
		heightLabel.setEditorKit(new HTMLEditorKit());
		heightLabel.setOpaque(false);
		heightLabel.setSelectionColor(new Color(0, 0, 0, 0));
		heightLabel.setText("Height (in tiles):");
		heightLabel.setBounds(10, 38, 100, 25);
		add(heightLabel);
		
		height = new JFormattedTextField(NumberFormat.getIntegerInstance());
		height.setBounds(110, 40, 150, 20);
		height.setBorder(new LineBorder(Color.GRAY, 1));
		add(height);
		
		final JSeparator seperator = new JSeparator();
		seperator.setBounds(8, 80, 260, 5);
		add(seperator);
		
		final JButton create = new JButton("Create");
		create.setActionCommand("create");
		create.addActionListener(buttonActionListener);
		create.setBounds(10, 90, 125, 25);
		add(create);
		
		final JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(buttonActionListener);
		cancel.setBounds(140, 90, 125, 25);
		add(cancel);
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	@Override
	public void setVisible(final boolean visible) {
		if(visible) dimension = null;
		
		super.setVisible(visible);
	}
}
