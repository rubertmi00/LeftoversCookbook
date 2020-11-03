package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.GUIConstants;

public class SelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int HEIGHT_PER_BUTTON = 65;
	private int prefH;
	private GridLayout layout;

	public SelectionPanel(int numButtons) {
		this.prefH = HEIGHT_PER_BUTTON * numButtons;
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new GridLayout(1, 1, 3, 3);
		this.setLayout(layout);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, prefH);
	}
	
	public GridLayout getLayout() {
		return layout;
	}

	public void setPrefH(int prefH) {
		this.prefH = prefH;
	}
	
	public void setPrefHButtons(int numButtons) {
		if(HEIGHT_PER_BUTTON * numButtons > GUIConstants.PREF_H_PANEL)
			this.prefH = HEIGHT_PER_BUTTON * numButtons;
		else
			this.prefH = GUIConstants.PREF_H_PANEL;
	}
}
