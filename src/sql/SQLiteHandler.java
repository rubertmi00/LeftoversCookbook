package sql;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteHandler {
	
	private static Connection con;
	
	public ResultSet displayCell(String table, String key, String row, String column) throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		Statement statement = con.createStatement();
		String query = "SELECT " + column + " FROM " + table + " WHERE " + key
				+ " = '" + row + "';";
		ResultSet res = statement.executeQuery(query);
		return res;
	}
	
	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:Recipes.db");
	}
	
	public void addRecipe(String name, int time, String ing, String inst,
			ArrayList<String> ingredientList, ArrayList<String> tagList) throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		PreparedStatement recipePrep = con.prepareStatement("INSERT INTO recipe VALUES(?, ?, ?, ?);");
		recipePrep.setString(1, name);
		recipePrep.setInt(2, time);
		recipePrep.setString(3, ing);
		recipePrep.setString(4, inst);
		recipePrep.execute();
		
		for(String s : ingredientList) {
			PreparedStatement recipeIngredientPrep =
					con.prepareStatement("INSERT INTO recipe_ingredient VALUES(?, ?);");
			recipeIngredientPrep.setString(1, name);
			recipeIngredientPrep.setString(2, s);
			recipeIngredientPrep.execute();
			
			Statement statement1 = con.createStatement();
			ResultSet resIngredient =
					statement1.executeQuery("SELECT ingredient_name FROM ingredients WHERE ingredient_name = '" + s + "';");
			if(!resIngredient.next()) {
				PreparedStatement ingredientPrep = con.prepareStatement("INSERT INTO ingredients VALUES(?);");
				ingredientPrep.setString(1, s);
				ingredientPrep.execute();
			}
		}
		
		for(String s : tagList) {
			PreparedStatement recipeTagPrep =
					con.prepareStatement("INSERT INTO recipe_tag VALUES(?, ?);");
			recipeTagPrep.setString(1, name);
			recipeTagPrep.setString(2, s);
			recipeTagPrep.execute();
			
			Statement statement2 = con.createStatement();
			ResultSet resTag =
					statement2.executeQuery("SELECT tag_name FROM tags WHERE tag_name = '" + s + "';");
			if(!resTag.next()) {
				PreparedStatement tagPrep = con.prepareStatement("INSERT INTO tags VALUES(?);");
				tagPrep.setString(1, s);
				tagPrep.execute();
			}
		}
	}
	
	public void removeRecipe(String name) throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		Statement statement = con.createStatement();
		statement.execute("DELETE FROM recipe WHERE recipe_name = '" + name + "';");
		statement.execute("DELETE FROM recipe_ingredient WHERE recipe_name = '" + name + "';");
		statement.execute("DELETE FROM recipe_tag WHERE recipe_name = '" + name + "';");
		
		ResultSet ingredients = displayTableColumn("ingredients", "ingredient_name");
		
		ArrayList<String> ingList = new ArrayList<String>();
		while(ingredients.next())
			ingList.add(ingredients.getString("ingredient_name"));
		
		for(String s : ingList) {
			ResultSet remainingConnections = statement.executeQuery("SELECT * FROM recipe_ingredient WHERE ingredient_name = '" + s + "';");
			if(!remainingConnections.next())
				statement.execute("DELETE FROM ingredients WHERE ingredient_name = '" + s + "';");
		}
		
		ResultSet tags = displayTableColumn("tags", "tag_name");
		
		ArrayList<String> tagList = new ArrayList<String>();
		while(tags.next())
			tagList.add(tags.getString("tag_name"));
		
		for(String s : tagList) {
			ResultSet remainingConnections = statement.executeQuery("SELECT * FROM recipe_tag WHERE tag_name = '" + s + "';");
			if(!remainingConnections.next())
				statement.execute("DELETE FROM tags WHERE tag_name = '" + s + "';");
		}
	}
	
	public ResultSet displayTableColumn(String table, String column) throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		Statement statement = con.createStatement();
		ResultSet res = statement.executeQuery("SELECT " + column + " FROM " + table);
		return res;
	}
	
	public ResultSet displayFullTable(String table) throws ClassNotFoundException, SQLException {
		return displayTableColumn(table, "*");
	}
	
	public ResultSet searchRecipes(String searchTerm) throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		Statement statement = con.createStatement();
		
		String query = "SELECT * FROM recipe ";
		if(!searchTerm.equals(""))
			query += " WHERE recipe_name LIKE '%" + searchTerm + "%';";
		
		ResultSet res = statement.executeQuery(query);
		return res;
	}
	
	public ResultSet filterRecipes(ArrayList<String> ingredientFilters, ArrayList<String> tagFilters, int timeFilter, String search)
			throws ClassNotFoundException, SQLException {
		if(con == null)
			getConnection();
		
		Statement statement = con.createStatement();
		String query = generateQuery(ingredientFilters, tagFilters, timeFilter, search);
		ResultSet res = statement.executeQuery(query);
		return res;
	}
	
	private String generateQuery(ArrayList<String> ingredientFilters, ArrayList<String> tagFilters, int timeFilter, String search) {
		String query = "SELECT * FROM recipe ";
		
		if(!tagFilters.isEmpty() || !ingredientFilters.isEmpty() || timeFilter >= 0 || !search.equals(""))
			query += " WHERE ";
		
		query = addFiltersToQuery(query, ingredientFilters, "recipe_ingredient", "ingredient_name");
		
		if(!tagFilters.isEmpty() && !ingredientFilters.isEmpty())
			query += " AND ";
		query = addFiltersToQuery(query, tagFilters, "recipe_tag", "tag_name");
		
		if(timeFilter >= 0 && (!tagFilters.isEmpty() || !ingredientFilters.isEmpty()))
			query += " AND ";
		
		if(timeFilter >= 0)
			query += " cook_time <= " + Integer.toString(timeFilter);
		
		if(!search.equals("") && (!tagFilters.isEmpty() || !ingredientFilters.isEmpty() || timeFilter >=0))
			query += " AND ";
		
		if(!search.equals(""))
			query += "recipe_name LIKE '%" + search + "%'";
		
		query += ";";
		return query;
	}
	
	private String addFiltersToQuery(String currentQuery, ArrayList<String> filters, String table, String column) {
		String query = currentQuery;
		
		for(String s : filters) {
			if(!s.equals(filters.get(0)))
				query += " AND ";
			query += " recipe_name IN (SELECT recipe_name FROM " + table + " WHERE " + column + " = '"
					+ s + "')";
		}
		
		return query;
	}
}
