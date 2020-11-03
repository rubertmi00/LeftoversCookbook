package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.GUIConstants;

public class FilterHeader extends JPanel {
	private static final long serialVersionUID = 1L;
	private BorderLayout layout;

	public FilterHeader(JButton backButton, JTextField searchInput, JTextField timeInput) {
		this.setBackground(GUIConstants.HEADER_COLOR);
		this.setFocusable(false);
		layout = new BorderLayout();
		this.setLayout(layout);
		
		this.add(backButton, BorderLayout.WEST);
		this.add(searchInput, BorderLayout.CENTER);
		this.add(timeInput, BorderLayout.EAST);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_HEADER);
	}
}
