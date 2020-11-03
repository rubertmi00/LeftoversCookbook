package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.GUIConstants;

public class FilterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridLayout layout;
	private FilterSubPanel ingredientFilters, tagFilters;

	public FilterPanel(int numIngButtons, int numTagButtons) {
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new GridLayout();
		this.setLayout(layout);
		
		ingredientFilters = new FilterSubPanel(numIngButtons);
		tagFilters = new FilterSubPanel(numTagButtons);
		Component ingScrollable = new JScrollPane(ingredientFilters);
		Component tagScrollable = new JScrollPane(tagFilters);
		ingScrollable.setBackground(GUIConstants.BODY_COLOR);
		tagScrollable.setBackground(GUIConstants.BODY_COLOR);
		
		this.add(ingScrollable);
		this.add(tagScrollable);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_PANEL);
	}

	public FilterSubPanel getIngredientFilters() {
		return ingredientFilters;
	}

	public FilterSubPanel getTagFilters() {
		return tagFilters;
	}
}
