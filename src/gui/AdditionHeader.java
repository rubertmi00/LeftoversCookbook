package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.GUIConstants;

public class AdditionHeader extends JPanel{
	private static final long serialVersionUID = 1L;
	private BorderLayout layout;

	public AdditionHeader(JButton backButton) {
		this.setBackground(GUIConstants.HEADER_COLOR);
		this.setFocusable(false);
		layout = new BorderLayout();
		this.setLayout(layout);
		
		this.add(backButton, BorderLayout.EAST);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_HEADER);
	}
}
