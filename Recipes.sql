-- Generate tables
CREATE TABLE recipe (
`recipe_name` VARCHAR(50) NOT NULL PRIMARY KEY,
`cook_time` INT NOT NULL,
'specific_ingredients' VARCHAR(1500) NOT NULL,
'instructions' VARCHAR(5000) NOT NULL
);

CREATE TABLE ingredients (
`ingredient_name` VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE recipe_ingredient (
`recipe_name` VARCHAR(50) NOT NULL,
`ingredient_name` VARCHAR(50) NOT NULL,
PRIMARY KEY(`recipe_name`, `ingredient_name`)
);

CREATE TABLE tags (
`tag_name` VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE recipe_tag (
`recipe_name` VARCHAR(50) NOT NULL,
`tag_name` VARCHAR(50) NOT NULL,
PRIMARY KEY(`recipe_name`, `tag_name`)
);

-- Populate tables
INSERT INTO recipe VALUES('Chicken Parm', 60, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('Spaghetti and Meatballs', 20, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('Hamburger', 30, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('Beef Wellington', 120, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('Pancakes', 20, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('French Toast', 35, 'placeholder', 'placeholder');
INSERT INTO recipe VALUES('Apple Cider Donuts',
240,
'2 3-inch cinnamon sticks\n3 cups apple cider\n1/2 cup apple butter\n1/2 cup buttermilk\n2 teaspoons vanilla extract\n1 tablespoon plus 2 teaspoons baking powder\n1 teaspoon kosher salt\n1/4 teaspoon baking soda\n1/4 teaspoon freshly grated nutmeg\n3 1/2 cups all-purpose flour, plus more for dusting\n1 tablespoon plus 1 teaspoon ground cinnamon, divided\n6 tablespoons unsalted butter, room temperature\n1/4 cup (packed) light brown sugar\n1 1/4 cups granulated sugar\n2 large eggs\nVegetable oil (for frying; about 4 cups)',
'Bring cinnamon sticks and apple cider to a boil in a large skillet over medium-high heat and cook until liquid is thick, syrupy, and reduced to about 1/3 cup, 20–30 minutes. Scrape into a medium bowl and whisk in apple butter, buttermilk, and vanilla until incorporated; set aside.\nWhisk baking powder, salt, baking soda, nutmeg, 3 1/2 cups flour, and 1 tsp. ground cinnamon in a medium bowl. Using an electric mixer on medium-high speed, beat butter, brown sugar, and 1/4 cup granulated sugar in a large bowl until light and fluffy, about 4 minutes. Add eggs one at a time, beating until well blended after each addition. Reduce mixer speed to low and add dry ingredients in 2 additions, alternating with cider mixture in 2 additions, starting with dry ingredients and ending with cider mixture (dough will be very soft and sticky).\nScrape dough onto a parchment lined-rimmed baking sheet thoroughly dusted with flour (about 1/3 cup). Dust hands and top of dough with more flour, then gently pat dough to ¾ in. thick. Dust with more flour and tightly cover with plastic wrap; chill dough at least 3 hours.\nWhisk remaining 1 cup granulated sugar and 1 Tbsp. cinnamon in a small bowl until no lumps remain.\nWorking on baking sheet, punch out as many rounds as you can with 3 1/4 in. cutter, then use 1 1/4 in. cutter to punch out center of each round. Gather doughnut scraps, reserving holes, and gently re-roll without overworking dough; repeat until all dough has been used (you should have 18 doughnuts).\nSet a wire rack inside a paper towel-lined rimmed baking sheet. Fit a large pot with deep-fry thermometer and pour in oil to a depth of 3 in.. Heat over medium-high until thermometer registers 350. Working in batches, fry doughnuts until deep golden brown, about 3 minutes per side. Transfer to prepared rack and let cool slightly. Fry doughnut holes until deep golden brown, about 2 minutes per side. Transfer to prepared rack and let cool slightly. Toss warm doughnuts and doughnut holes in cinnamon sugar.');
INSERT INTO recipe VALUES('Stovetop Mac and Cheese',
15,
'8 oz Pasta\n1 cup milk\n1.5 cups water\nPanko bread crumbs\n2 oz grated romano cheese\n4 oz american cheese (shredded)\n1/2 tsp dijon mustard\n4 oz shard cheddar (shredded)\nCayenne',
'Simmer milk and water in medium saucepan. Toast breadcrumbs in melted butter. Add pasta once milk is simmering. Cook pasta on medium-low heat until all liquid is absorbed. Stir frequently. Flip breadcrumbs while pasta is cooking.\nRemove breadcrumbs from heat and toss with romano cheese once golden brown. Once pasta is done cooking, add american cheese and dijon mustard over very low heat. Stir. Once the cheese is melted, add cheddar and a shake of cayenne. Remove the pasta from heat, cover, and let sit for five minutes. Top finished mac and cheese with breadcrumbs.');
INSERT INTO recipe VALUES('Chocolate Caramel PB Cookies',
30,
'8 ½ ounces flour\n1 teaspoon baking soda\n1 teaspoon kosher salt\n2 sticks unsalted butter (room temperature)\n¾ cup granulated sugar\n¾ cup brown sugar\n1 teaspoon vanilla extract\n2 large eggs\nChocolate chips\nPeanut butter chips\nCaramel squares',
'Unwrap caramel squares and cut each square into fours. Then, roll each small caramel square into a ball.\nWhisk 8 ½ ounces of flour, 1 teaspoon of baking soda, and 1 teaspoon of kosher salt. Set aside for later. \nIn a separate bowl, place 2 sticks of unsalted butter (room temperature), ¾ cup of granulated sugar, and a firmly packed ¾ cup of brown sugar. Using a stand mixer, mix the ingredients together and add 1 teaspoon of vanilla extract.\nAfter about one minute or after all of the ingredients are mixed together, add 2 large eggs to the mixture. \nScrape down the sides of the bowl and mix for about 1-2 minutes or until the mixture is a creamy texture.\nScrape down the sides of the bowl one last time and then add the dry ingredients to the mixture. Mix for no more than 30-45 seconds. Be sure not to over mix unless you want tough cookies.\nAdd your chopped up caramel squares, chocolate chips, and peanut butter chips to the mixture. Make sure to scrape the cookie dough out of the paddle attachment. \nUse a tiny ice cream scoop or melon baller to scoop out uniform little nubbins (1 Tablespoon worth) of the cookie dough and space out the scoops evenly on a parchment lined baking sheet.\nPlace into a 375 deg F oven for 10-12 minutes.\nLet cool completely.');

INSERT INTO ingredients VALUES('Chicken');
INSERT INTO ingredients VALUES('Beef');
INSERT INTO ingredients VALUES('Pasta');
INSERT INTO ingredients VALUES('Flour');
INSERT INTO ingredients VALUES('Eggs');
INSERT INTO ingredients VALUES('Butter');
INSERT INTO ingredients VALUES('Cheese');
INSERT INTO ingredients VALUES('Chocolate');
INSERT INTO ingredients VALUES('Peanut Butter');

INSERT INTO recipe_ingredient VALUES('Chicken Parm', 'Chicken');
INSERT INTO recipe_ingredient VALUES('Spaghetti and Meatballs', 'Beef');
INSERT INTO recipe_ingredient VALUES('Spaghetti and Meatballs', 'Pasta');
INSERT INTO recipe_ingredient VALUES('Hamburger', 'Beef');
INSERT INTO recipe_ingredient VALUES('Beef Wellington', 'Beef');
INSERT INTO recipe_ingredient VALUES('Beef Wellington', 'Flour');
INSERT INTO recipe_ingredient VALUES('Pancakes', 'Flour');
INSERT INTO recipe_ingredient VALUES('French Toast', 'Flour');
INSERT INTO recipe_ingredient VALUES('Pancakes', 'Eggs');
INSERT INTO recipe_ingredient VALUES('French Toast', 'Eggs');
INSERT INTO recipe_ingredient VALUES('Pancakes', 'Butter');
INSERT INTO recipe_ingredient VALUES('French Toast', 'Butter');
INSERT INTO recipe_ingredient VALUES('Apple Cider Donuts', 'Eggs');
INSERT INTO recipe_ingredient VALUES('Apple Cider Donuts', 'Flour');
INSERT INTO recipe_ingredient VALUES('Apple Cider Donuts', 'Butter');
INSERT INTO recipe_ingredient VALUES('Stovetop Mac and Cheese', 'Pasta');
INSERT INTO recipe_ingredient VALUES('Stovetop Mac and Cheese', 'Cheese');
INSERT INTO recipe_ingredient VALUES('Chocolate Caramel PB Cookies', 'Chocolate');
INSERT INTO recipe_ingredient VALUES('Chocolate Caramel PB Cookies', 'Flour');
INSERT INTO recipe_ingredient VALUES('Chocolate Caramel PB Cookies', 'Peanut Butter');

INSERT INTO tags VALUES('Breakfast');
INSERT INTO tags VALUES('Sides');
INSERT INTO tags VALUES('Dinner');
INSERT INTO tags VALUES('Dessert');

INSERT INTO recipe_tag VALUES('Chicken Parm', 'Dinner');
INSERT INTO recipe_tag VALUES('Spaghetti and Meatballs', 'Dinner');
INSERT INTO recipe_tag VALUES('Hamburger', 'Dinner');
INSERT INTO recipe_tag VALUES('Beef Wellington', 'Dinner');
INSERT INTO recipe_tag VALUES('Pancakes', 'Breakfast');
INSERT INTO recipe_tag VALUES('French Toast', 'Breakfast');
INSERT INTO recipe_tag VALUES('Apple Cider Donuts', 'Dessert');
INSERT INTO recipe_tag VALUES('Stovetop Mac and Cheese', 'Sides');
INSERT INTO recipe_tag VALUES('Chocolate Caramel PB Cookies', 'Dessert');