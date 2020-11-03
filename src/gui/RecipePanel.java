package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.GUIConstants;

public class RecipePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridLayout layout;

	private JTextArea ingredientsArea, recipeArea;

	public RecipePanel(String ingredientsList, String fullRecipe) {
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new GridLayout(2, 1, 5, 5);
		this.setLayout(layout);
		
		this.ingredientsArea = new JTextArea(ingredientsList, 1, 100);
		this.recipeArea = new JTextArea(fullRecipe, 1, 100);
		
		stylize(ingredientsArea);
		stylize(recipeArea);
		
		this.add(new JScrollPane(ingredientsArea));
		this.add(new JScrollPane(recipeArea));
	}
	
	public RecipePanel() {
		this("", "");
	}
	
	private void stylize(JTextArea area) {
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setBorder(GUIConstants.BODY_BORDER);
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_PANEL);
	}

	public JTextArea getIngredientsArea() {
		return ingredientsArea;
	}

	public JTextArea getRecipeArea() {
		return recipeArea;
	}
}
