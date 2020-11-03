# LeftoversCookbook
A digital cookbook which allows users to store recipes in an SQLite3 database, and later search for certain recipes by filtering the database based on attributes such as ingredients, cook time, name, or meal type.

Code by: Michael Ruberto.

Notes:
- When adding recipes to the database, the "List of Ingredients..." and "Instructions..." fields are for the actual text to be read by the chef. The "Ingredient Filters (Separate With Commas)..." and "Tag Filters (Separate With Commas)..." fields are what is used to create the buttons which filter the recipes. These should be main ingredients such as "Chicken" or "Flour" and not minor ingredients such as "Salt" or "Oregano". The tags can be meals or types of food such as "Breakfast" or "Sandwich"

- When applying multiple filters, the filters will be evaluated as ANDs, not ORs. For example, if you apply the filters "Flour" and "Breakfast", it will return all the recipes for breakfast foods made with flour, not all the recipes which make breakfast foods or the recipes which use flour.
