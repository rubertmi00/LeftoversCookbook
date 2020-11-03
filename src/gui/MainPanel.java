package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.GUIConstants;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private CardLayout layout;
	private Component scrollableSelection;
	private SelectionPanel selectionPanel;
	private RecipePanel recipePanel;
	private FilterPanel filterPanel;
	private AdditionPanel additionPanel;

	public MainPanel(ArrayList<JButton> selectionButtons, ArrayList<JButton> ingredientButtons, ArrayList<JButton> tagButtons){
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new CardLayout();
		this.setLayout(layout);
		selectionPanel = new SelectionPanel(selectionButtons.size());
		recipePanel = new RecipePanel();
		filterPanel = new FilterPanel(ingredientButtons.size(), tagButtons.size());
		additionPanel = new AdditionPanel();
		
		for (JButton b : selectionButtons) {
			selectionPanel.add(b);
		}
		
		GridLayout ingLayout = (GridLayout)this.filterPanel.getIngredientFilters().getLayout();
		ingLayout.setRows(ingredientButtons.size());
		for (JButton b : ingredientButtons) {
			filterPanel.getIngredientFilters().add(b);
		}
		
		GridLayout tagLayout = (GridLayout)this.filterPanel.getTagFilters().getLayout();
		tagLayout.setRows(tagButtons.size());
		for (JButton b : tagButtons) {
			filterPanel.getTagFilters().add(b);
		}
		
		selectionPanel.getLayout().setRows(selectionButtons.size());
		scrollableSelection = new JScrollPane(selectionPanel);
		scrollableSelection.setBackground(GUIConstants.BODY_COLOR);
		
		this.add(scrollableSelection, "Selection Panel");
		this.add(recipePanel, "Recipe Panel");
		this.add(filterPanel, "Filter Panel");
		this.add(additionPanel, "Addition Panel");
	}
	
	public void showRecipe(String ingredients, String instructions) {
		recipePanel.getIngredientsArea().setText(ingredients);
		recipePanel.getRecipeArea().setText(instructions);
		this.layout.show(this, "Recipe Panel");
	}
	
	public void showSelection(ArrayList<JButton> buttons) {
		selectionPanel.removeAll();
		
		for (JButton b : buttons) {
			selectionPanel.add(b);
		}
		
		selectionPanel.setPrefHButtons(buttons.size());
		selectionPanel.getLayout().setRows(buttons.size());
		
		this.layout.show(this, "Selection Panel");
	}
	
	public void showFilter(ArrayList<JButton> ingButtons, ArrayList<JButton> tagButtons) {
		filterPanel.getIngredientFilters().removeAll();
		filterPanel.getTagFilters().removeAll();
		
		for (JButton b : ingButtons) {
			filterPanel.getIngredientFilters().add(b);
		}
		for (JButton b : tagButtons) {
			filterPanel.getTagFilters().add(b);
		}
		
		GridLayout filterLayoutIng = (GridLayout) filterPanel.getIngredientFilters().getLayout();
		filterLayoutIng.setRows(ingButtons.size());
		GridLayout filterLayoutTag = (GridLayout) filterPanel.getTagFilters().getLayout();
		filterLayoutTag.setRows(tagButtons.size());
		this.layout.show(this, "Filter Panel");
	}
	
	public void showAddition() {
		this.layout.show(this, "Addition Panel");
	}

	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_PANEL);
	}
}