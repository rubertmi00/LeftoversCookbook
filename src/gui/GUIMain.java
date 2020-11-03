package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
import util.ButtonComparator;
import util.GUIConstants;
import util.Sanitizer;

public class GUIMain extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int PREF_W = 800;
	private static final int PREF_H = 600;
	private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
	private final String DEFAULT_SEARCH_TEXT = "Search By Name...";
	private final String DEFAULT_TIME_TEXT = "Cook Time...";

	private MainPanel mainPanel;
	private HeaderPanel headerPanel;
	private ArrayList<JButton> selectionButtons, filterButtonsIngredients, filterButtonsTags;
	private JTextField cookTimeInput, searchInput;
	private ArrayList<String> ingredientFilters, tagFilters;
	private int timeFilter;
	
	private SQLiteHandler handler;
	private ResultSet relevantRecipes, possibleIngredientFilters, possibleTagFilters;

	public GUIMain() {
		setBackground(GUIConstants.BODY_COLOR);
		this.setFocusable(true);
		this.setLayout(new BorderLayout());

		this.ingredientFilters = new ArrayList<String>();
		this.tagFilters = new ArrayList<String>();
		this.timeFilter = -1;
		this.selectionButtons = new ArrayList<JButton>();
		this.filterButtonsIngredients = new ArrayList<JButton>();
		this.filterButtonsTags = new ArrayList<JButton>();
		this.cookTimeInput = new JTextField();
		this.searchInput = new JTextField();
		handler = new SQLiteHandler();

		setUpButtons();

		mainPanel = new MainPanel(selectionButtons, filterButtonsIngredients, filterButtonsTags);
		this.add(mainPanel, BorderLayout.CENTER);

		JButton rhBackButton = new JButton();
		rhBackButton.setText("Back");
		rhBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonPressed();
			}
		});

		JButton fhBackButton = new JButton();
		fhBackButton.setText("Back");
		fhBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonPressed();
			}
		});
		
		JButton ahBackButton = new JButton();
		ahBackButton.setText("Back");
		ahBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backButtonPressed();
			}
		});

		JButton shFilterButton = new JButton();
		shFilterButton.setText("Filter Recipes");
		shFilterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterRecipeButtonPressed();
			}
		});
		
		JButton shAddButton = new JButton();
		shAddButton.setText("Add a Recipe");
		shAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRecipeButtonPressed();
			}
		});
		
		formatInputs(cookTimeInput, DEFAULT_TIME_TEXT, 20);
		formatInputs(searchInput, DEFAULT_SEARCH_TEXT, 100);

		headerPanel = new HeaderPanel(rhBackButton, shFilterButton, fhBackButton, searchInput, cookTimeInput, ahBackButton, shAddButton);
		this.add(headerPanel, BorderLayout.NORTH);
	}
	
	public void setUpButtons() {
		try {
			relevantRecipes = handler.filterRecipes(ingredientFilters, tagFilters, timeFilter, "");
			possibleIngredientFilters = handler.displayTableColumn("ingredients", "ingredient_name");
			possibleTagFilters = handler.displayTableColumn("tags", "tag_name");

			generateSelectionButtons(relevantRecipes);
			generateFilterButtons(filterButtonsIngredients, possibleIngredientFilters, "ingredient_name");
			generateFilterButtons(filterButtonsTags, possibleTagFilters, "tag_name");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void formatInputs(JTextField input, String defaultText, int cols) {
		input.setText(defaultText);
		input.setColumns(cols);
		input.setForeground(Color.LIGHT_GRAY);
		input.setBorder(GUIConstants.HEADER_BORDER);
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

	private void generateFilterButtons(ArrayList<JButton> list, ResultSet rs, String column) throws SQLException {
		list.clear();

		while (rs.next()) {
			String name = rs.getString(column);

			JButton tempButton = new JButton();
			tempButton.setText(name);
			tempButton.setBackground(GUIConstants.BODY_COLOR);
			tempButton.setOpaque(true);
			tempButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterButtonPressed(list, e.getSource());
				}
			});
			
			if(ingredientFilters.contains(name) || tagFilters.contains(name))
				tempButton.setForeground(GUIConstants.HEADER_COLOR);
			
			list.add(tempButton);
		}
		
		list.sort(new ButtonComparator());
	}

	private void generateSelectionButtons(ResultSet recipes) throws SQLException {
		selectionButtons.clear();

		while (recipes.next()) {
			String recipeName = recipes.getString("recipe_name");
			int cookTime = recipes.getInt("cook_time");
			String ingredients = recipes.getString("specific_ingredients").replace("\\n", "\n");
			String instructions = recipes.getString("instructions").replace("\\n", "\n\n");

			JButton tempButton = new JButton();
			tempButton.setText(recipeName);
			tempButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					recipeButtonPressed(recipeName, cookTime, ingredients, instructions);
				}
			});
			selectionButtons.add(tempButton);
		}
		
		selectionButtons.sort(new ButtonComparator());
	}

	private void filterButtonPressed(ArrayList<JButton> list, Object source) {
		JButton button = null;
		if (source instanceof JButton)
			button = (JButton) source;

		ArrayList<String> filterList = null;
		if (list.equals(this.filterButtonsIngredients))
			filterList = this.ingredientFilters;
		if (list.equals(this.filterButtonsTags))
			filterList = this.tagFilters;

		if (filterList.contains(button.getText()))
			filterList.remove(button.getText());
		else
			filterList.add(button.getText());

		if (ingredientFilters.contains(button.getText()) || tagFilters.contains(button.getText())) {
			button.setForeground(GUIConstants.HEADER_COLOR);
		} else {
			button.setForeground(Color.BLACK);
		}
	}

	private void recipeButtonPressed(String recipeName, int cookTime, String ingredients, String instructions) {
		mainPanel.showRecipe(ingredients, instructions);
		headerPanel.showRecipe(recipeName, cookTime);
	}

	private void backButtonPressed() {
		try {
			if(!cookTimeInput.getText().equals(DEFAULT_TIME_TEXT))
				timeFilter = Integer.parseInt(cookTimeInput.getText());
			else
				timeFilter = -1;
			
			String search = Sanitizer.sanitizeInput(searchInput.getText());
			if (search.equals(DEFAULT_SEARCH_TEXT))
				search = "";
			
			relevantRecipes = handler.filterRecipes(ingredientFilters, tagFilters, timeFilter, search);
			generateSelectionButtons(relevantRecipes);
			
			headerPanel.showSelection();
			mainPanel.showSelection(selectionButtons);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "The 'Cook Time' Filter must be an integer value.");
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void filterRecipeButtonPressed() {
		setUpButtons();
		
		headerPanel.showFilter();
		mainPanel.showFilter(filterButtonsIngredients, filterButtonsTags);
	}
	
	private void addRecipeButtonPressed() {
		headerPanel.showAddition();
		mainPanel.showAddition();
	}
	
	public void formatFilterInput(JTextField tf, String hint) {
		Runnable format = new Runnable() {
			@Override
			public void run() {
				if (!tf.getText().equals(hint) &&
						tf.getText().contains(hint)) {
					tf.setText(tf.getText().replace(hint, ""));
					tf.setForeground(Color.BLACK);
				}
				else if (tf.getText().equals("")) {
					tf.setText(hint);
					tf.setForeground(Color.LIGHT_GRAY);
				}
			}
		};
		SwingUtilities.invokeLater(format);
	}

	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(hints);
	}
}
