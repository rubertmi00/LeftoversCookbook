package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sql.SQLiteHandler;
import util.GUIConstants;
import util.Sanitizer;

public class AdditionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final String DEFAULT_NAME_TEXT = "Recipe Name...";
	private final String DEFAULT_TIME_TEXT = "Cook Time...";
	private final String DEFAULT_INGREDIENTS_TEXT = "List of Ingredients...";
	private final String DEFAULT_INSTRUCTIONS_TEXT = "Instructions...";
	private final String DEFAULT_ING_FILTERS_TEXT = "Ingredient Filters (Separate With Commas)...";
	private final String DEFAULT_TAG_FILTERS_TEXT = "Tag Filters (Separate With Commas)...";
	private GridLayout layout;
	private JButton addRecipe;
	private JTextField name, time, ingredients, instructions, ingFilters, tagFilters;

	public AdditionPanel() {
		this.setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(false);
		this.layout = new GridLayout(4, 2, 5, 5);
		this.setLayout(layout);

		this.name = new JTextField();
		this.time = new JTextField();
		this.ingredients = new JTextField();
		this.instructions = new JTextField();
		this.ingFilters = new JTextField();
		this.tagFilters = new JTextField();

		formatInputs(name, this.DEFAULT_NAME_TEXT);
		formatInputs(time, this.DEFAULT_TIME_TEXT);
		formatInputs(ingredients, this.DEFAULT_INGREDIENTS_TEXT);
		formatInputs(instructions, this.DEFAULT_INSTRUCTIONS_TEXT);
		formatInputs(ingFilters, this.DEFAULT_ING_FILTERS_TEXT);
		formatInputs(tagFilters, this.DEFAULT_TAG_FILTERS_TEXT);

		this.addRecipe = new JButton();
		addRecipe.setText("Add Recipe to Database");
		addRecipe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRecipeToDatabase();
			}
		});

		this.add(name);
		this.add(time);
		this.add(ingredients);
		this.add(instructions);
		this.add(ingFilters);
		this.add(tagFilters);
		this.add(addRecipe);
	}

	private void formatInputs(JTextField input, String defaultText) {
		input.setText(defaultText);
		input.setForeground(Color.LIGHT_GRAY);
		input.setBorder(GUIConstants.BODY_BORDER);
		input.setHorizontalAlignment(JTextField.CENTER);
		input.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}

			public void removeUpdate(DocumentEvent e) {
				formatFilterInput(input, defaultText);
			}

			public void insertUpdate(DocumentEvent e) {
				formatFilterInput(input, defaultText);
			}
		});
	}

	private void formatFilterInput(JTextField tf, String hint) {
		Runnable format = new Runnable() {
			@Override
			public void run() {
				if (!tf.getText().equals(hint) && tf.getText().contains(hint)) {
					tf.setText(tf.getText().replace(hint, ""));
					tf.setForeground(Color.BLACK);
				} else if (tf.getText().equals("")) {
					tf.setText(hint);
					tf.setForeground(Color.LIGHT_GRAY);
				}
			}
		};
		SwingUtilities.invokeLater(format);
	}

	private void addRecipeToDatabase() {
		SQLiteHandler handler = new SQLiteHandler();

		try {
			String recipeName = Sanitizer.sanitizeInput(name.getText());
			int cookTime = Integer.parseInt(time.getText());
			String recipeIngredients = Sanitizer.sanitizeInput(ingredients.getText());
			String recipeInstructions = Sanitizer.sanitizeInput(instructions.getText());
			ArrayList<String> ingredientList = parseStringList(ingFilters.getText());
			ArrayList<String> tagList = parseStringList(tagFilters.getText());

			if (!recipeName.equals(DEFAULT_NAME_TEXT) && !time.getText().equals(DEFAULT_TIME_TEXT)
					&& !ingredients.getText().equals(DEFAULT_INGREDIENTS_TEXT)
					&& !instructions.getText().equals(DEFAULT_INSTRUCTIONS_TEXT)
					&& !ingFilters.getText().equals(DEFAULT_ING_FILTERS_TEXT)
					&& !tagFilters.getText().equals(DEFAULT_TAG_FILTERS_TEXT)) {
				clearFields();
				handler.addRecipe(recipeName, cookTime, recipeIngredients, recipeInstructions, ingredientList, tagList);
			}
			else
				JOptionPane.showMessageDialog(this, "You must input a value for each field");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "The 'Cook Time' Input must be an integer value.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> parseStringList(String input) {
		ArrayList<String> filters = new ArrayList<String>();
		String str = input.replace(" ", "");
		
		while(str.indexOf(",") > 0) {
			filters.add(str.substring(0, str.indexOf(",")));
			str = str.substring(str.indexOf(",") + 1);
		}
		
		filters.add(str);
		
		return filters;
	}
	
	private void clearFields() {
		name.setText(DEFAULT_NAME_TEXT);
		name.setForeground(Color.LIGHT_GRAY);
		time.setText(DEFAULT_TIME_TEXT);
		time.setForeground(Color.LIGHT_GRAY);
		ingredients.setText(DEFAULT_INGREDIENTS_TEXT);
		ingredients.setForeground(Color.LIGHT_GRAY);
		instructions.setText(DEFAULT_INSTRUCTIONS_TEXT);
		instructions.setForeground(Color.LIGHT_GRAY);
		ingFilters.setText(DEFAULT_ING_FILTERS_TEXT);
		ingFilters.setForeground(Color.LIGHT_GRAY);
		tagFilters.setText(DEFAULT_TAG_FILTERS_TEXT);
		tagFilters.setForeground(Color.LIGHT_GRAY);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(GUIConstants.PREF_W, GUIConstants.PREF_H_PANEL);
	}
}
