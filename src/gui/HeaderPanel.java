package gui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.GUIConstants;


public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private CardLayout layout;
	private SelectionHeader sh;
	private RecipeHeader rh;
	private FilterHeader fh;
	private AdditionHeader ah;

	public HeaderPanel(JButton rhBackButton, JButton bhFilterButton, JButton fhBackButton,JTextField searchInput, JTextField timeInput, JButton ahBackButton, JButton shAddButton) {
		this.setBackground(GUIConstants.HEADER_COLOR);
		this.setFocusable(false);
		this.layout = new CardLayout();
		this.setLayout(layout);
		
		sh = new SelectionHeader(bhFilterButton, shAddButton);
		rh = new RecipeHeader(rhBackButton);
		fh = new FilterHeader(fhBackButton, searchInput, timeInput);
		ah = new AdditionHeader(ahBackButton);
		
		this.add(sh, "Selection Header");
		this.add(rh, "Recipe Header");
		this.add(fh, "Filter Header");
		this.add(ah, "Addition Header");
	}
	
	public void showRecipe(String recipeName, int cookTime) {
		rh.getNameArea().setText(recipeName);
		rh.getTimeArea().setText("Cook Time: " + cookTime + " Minutes");
		this.layout.show(this, "Recipe Header");
	}
	
	public void showSelection() {
		this.layout.show(this, "Selection Header");
	}
	
	public void showAddition() {
		this.layout.show(this, "Addition Header");
	}
	
	public void showFilter() {
		this.layout.show(this, "Filter Header");		
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_HEADER);
	}
}
