package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.GUIConstants;

public class SelectionHeader extends JPanel {
	private static final long serialVersionUID = 1L;
	private BorderLayout layout;
	private JTextField wordmark;

	public SelectionHeader(JButton filterButton, JButton addButton) {
		this.setBackground(GUIConstants.HEADER_COLOR);
		this.setFocusable(false);
		this.layout = new BorderLayout();
		this.setLayout(layout);
		
		wordmark = new JTextField("Leftovers: Digital Cookbook");
		wordmark.setFont(GUIConstants.HEADER_FONT_1);
		wordmark.setBorder(GUIConstants.HEADER_BORDER);
		wordmark.setEditable(false);
		wordmark.setBackground(GUIConstants.HEADER_COLOR);
		wordmark.setForeground(GUIConstants.HEADER_TEXT_COLOR);
		wordmark.setHorizontalAlignment(JTextField.CENTER);
		
		this.add(wordmark, BorderLayout.CENTER);
		this.add(filterButton, BorderLayout.EAST);
		this.add(addButton, BorderLayout.WEST);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_HEADER);
	}
}
