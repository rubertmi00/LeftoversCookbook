package sql;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteTester {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SQLiteHandler handler = new SQLiteHandler();
		ResultSet recipes, ingredients, tags, filtered, searched, allTest;
		
		try {
			allTest = handler.displayFullTable("recipe");
			recipes = handler.displayTableColumn("recipe", "recipe_name");
			ingredients = handler.displayTableColumn("ingredients", "ingredient_name");
			tags = handler.displayTableColumn("tags", "tag_name");
			
			ArrayList<String> ingredientFilters = new ArrayList<String>();
			ArrayList<String> tagFilters = new ArrayList<String>();
			int timeFilter = 40;
			
			filtered = handler.filterRecipes(ingredientFilters, tagFilters, timeFilter, "ch");
			
			ArrayList<String> hdIng = new ArrayList<String>();
			hdIng.add("Beef");
			hdIng.add("1");
			ArrayList<String> hdTag = new ArrayList<String>();
			hdTag.add("Dinner");
			hdTag.add("1");
//			handler.addRecipe("Hot Dog", 10, "placeholder", "placeholder", hdIng, hdTag);
			handler.removeRecipe("Chili");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
