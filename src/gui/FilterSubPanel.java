package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.GUIConstants;

public class FilterSubPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final int PREF_W = 310;
	private final int HEIGHT_PER_BUTTON = 65;
	private int prefH;
	private GridLayout layout;

	public FilterSubPanel(int numButtons) {
		this.prefH = HEIGHT_PER_BUTTON * numButtons;
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new GridLayout();
		layout = new GridLayout(1, 1, 3, 3);
		this.setLayout(layout);
		this.setBackground(GUIConstants.BODY_COLOR);
	}

	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, prefH);
	}
}
