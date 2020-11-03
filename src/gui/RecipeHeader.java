package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.GUIConstants;

public class RecipeHeader extends JPanel {
	private static final long serialVersionUID = 1L;
	private BorderLayout layout;
	private JTextField nameArea, timeArea;

	public RecipeHeader(JButton backButton) {
		this.setBackground(GUIConstants.HEADER_COLOR);
		this.setFocusable(false);
		layout = new BorderLayout();
		this.setLayout(layout);
		
		this.nameArea = new JTextField();
		this.timeArea = new JTextField();
		stylize(nameArea);
		stylize(timeArea);
		
		this.add(backButton, BorderLayout.WEST);
		this.add(nameArea, BorderLayout.CENTER);
		this.add(timeArea, BorderLayout.EAST);
	}
	
	private void stylize(JTextField field) {
		field.setFont(GUIConstants.HEADER_FONT_1);
		field.setBorder(GUIConstants.HEADER_BORDER);
		field.setEditable(false);
		field.setBackground(GUIConstants.HEADER_COLOR);
		field.setForeground(GUIConstants.HEADER_TEXT_COLOR);
		field.setHorizontalAlignment(JTextField.CENTER);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_HEADER);
	}

	public JTextField getNameArea() {
		return nameArea;
	}

	public JTextField getTimeArea() {
		return timeArea;
	}
}
