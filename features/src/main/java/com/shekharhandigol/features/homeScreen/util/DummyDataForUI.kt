package com.shekharhandigol.features.homeScreen.util

import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse


val recipeDetailDummy = RecipeDetailsResponse(
    aggregateLikes = 209,
    analyzedInstructions = emptyList(),
    cheap = false,
    cookingMinutes = 25,
    creditsText = "Full Belly Sisters",
    cuisines = emptyList(),
    dairyFree = false,
    diets = emptyList(),
    dishTypes = listOf("lunch", "main course", "main dish", "dinner"),
    extendedIngredients = listOf(
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Milk, Eggs, Other Dairy",
            amount = 1.0,
            consistency = "solid",
            id = 1001,
            image = "butter-sliced.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 1.0,
                    unitLong = "Tbsp",
                    unitShort = "Tbsp"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 1.0,
                    unitLong = "Tbsp",
                    unitShort = "Tbsp"
                )
            ),
            meta = emptyList(),
            name = "butter",
            nameClean = "butter",
            original = "1 tbsp butter",
            originalName = "butter",
            unit = "tbsp"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Produce",
            amount = 2.0,
            consistency = "solid",
            id = 10011135,
            image = "cauliflower.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 473.176,
                    unitLong = "milliliters",
                    unitShort = "ml"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 2.0,
                    unitLong = "cups",
                    unitShort = "cups"
                )
            ),
            meta = listOf("frozen", "thawed", "cut into bite-sized pieces"),
            name = "cauliflower florets",
            nameClean = "cauliflower florets",
            original = "about 2 cups frozen cauliflower florets, thawed, cut into bite-sized pieces",
            originalName = "about frozen cauliflower florets, thawed, cut into bite-sized pieces",
            unit = "cups"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Cheese",
            amount = 2.0,
            consistency = "solid",
            id = 1041009,
            image = "cheddar-cheese.png",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 2.0,
                    unitLong = "Tbsps",
                    unitShort = "Tbsps"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 2.0,
                    unitLong = "Tbsps",
                    unitShort = "Tbsps"
                )
            ),
            meta = listOf("grated", "(I used romano)"),
            name = "cheese",
            nameClean = "cheese",
            original = "2 tbsp grated cheese (I used romano)",
            originalName = "grated cheese (I used romano)",
            unit = "tbsp"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Oil, Vinegar, Salad Dressing",
            amount = 1.0,
            consistency = "liquid",
            id = 1034053,
            image = "olive-oil.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 1.0,
                    unitLong = "Tbsp",
                    unitShort = "Tbsp"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 1.0,
                    unitLong = "Tbsp",
                    unitShort = "Tbsp"
                )
            ),
            meta = emptyList(),
            name = "extra virgin olive oil",
            nameClean = "extra virgin olive oil",
            original = "1-2 tbsp extra virgin olive oil",
            originalName = "extra virgin olive oil",
            unit = "tbsp"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Produce",
            amount = 5.0,
            consistency = "solid",
            id = 11215,
            image = "garlic.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 5.0,
                    unitLong = "cloves",
                    unitShort = "cloves"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 5.0,
                    unitLong = "cloves",
                    unitShort = "cloves"
                )
            ),
            meta = emptyList(),
            name = "garlic",
            nameClean = "garlic",
            original = "5-6 cloves garlic",
            originalName = "garlic",
            unit = "cloves"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Pasta and Rice",
            amount = 6.0,
            consistency = "solid",
            id = 20420,
            image = "fusilli.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 170.097,
                    unitLong = "grams",
                    unitShort = "g"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 6.0,
                    unitLong = "ounces",
                    unitShort = "oz"
                )
            ),
            meta = listOf("(I used linguine)"),
            name = "pasta",
            nameClean = "pasta",
            original = "6-8 ounces pasta (I used linguine)",
            originalName = "pasta (I used linguine)",
            unit = "ounces"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Spices and Seasonings",
            amount = 2.0,
            consistency = "solid",
            id = 1032009,
            image = "red-pepper-flakes.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 2.0,
                    unitLong = "pinches",
                    unitShort = "pinches"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 2.0,
                    unitLong = "pinches",
                    unitShort = "pinches"
                )
            ),
            meta = listOf("red"),
            name = "red pepper flakes",
            nameClean = "red pepper flakes",
            original = "couple of pinches red pepper flakes, optional",
            originalName = "couple of red pepper flakes, optional",
            unit = "pinches"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Spices and Seasonings",
            amount = 2.0,
            consistency = "solid",
            id = 1102047,
            image = "salt-and-pepper.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 2.0,
                    unitLong = "servings",
                    unitShort = "servings"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 2.0,
                    unitLong = "servings",
                    unitShort = "servings"
                )
            ),
            meta = listOf("to taste"),
            name = "salt and pepper",
            nameClean = "salt and pepper",
            original = "salt and pepper, to taste",
            originalName = "salt and pepper, to taste",
            unit = "servings"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Produce",
            amount = 3.0,
            consistency = "solid",
            id = 11291,
            image = "spring-onions.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 3.0,
                    unitLong = "",
                    unitShort = ""
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 3.0,
                    unitLong = "",
                    unitShort = ""
                )
            ),
            meta = listOf("white", "green", "separated", "chopped"),
            name = "scallions",
            nameClean = "scallions",
            original = "3 scallions, chopped, white and green parts separated",
            originalName = "scallions, chopped, white and green parts separated",
            unit = ""
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Alcoholic Beverages",
            amount = 2.0,
            consistency = "liquid",
            id = 14106,
            image = "white-wine.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 2.0,
                    unitLong = "Tbsps",
                    unitShort = "Tbsps"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 2.0,
                    unitLong = "Tbsps",
                    unitShort = "Tbsps"
                )
            ),
            meta = listOf("white"),
            name = "white wine",
            nameClean = "white wine",
            original = "2-3 tbsp white wine",
            originalName = "white wine",
            unit = "tbsp"
        ),
        RecipeDetailsResponse.ExtendedIngredient(
            aisle = "Pasta and Rice",
            amount = 0.25,
            consistency = "solid",
            id = 99025,
            image = "breadcrumbs.jpg",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(
                metric = RecipeDetailsResponse.ExtendedIngredient.Measures.Metric(
                    amount = 59.147,
                    unitLong = "milliliters",
                    unitShort = "ml"
                ),
                us = RecipeDetailsResponse.ExtendedIngredient.Measures.Us(
                    amount = 0.25,
                    unitLong = "cups",
                    unitShort = "cups"
                )
            ),
            meta = listOf("whole wheat", "(I used panko)"),
            name = "whole wheat bread crumbs",
            nameClean = "whole wheat bread crumbs",
            original = "1/4 cup whole wheat bread crumbs (I used panko)",
            originalName = "whole wheat bread crumbs (I used panko)",
            unit = "cup"
        )
    ),
    gaps = "no",
    glutenFree = false,
    healthScore = 19.0,
    id = 716429,
    image = "https://img.spoonacular.com/recipes/716429-556x370.jpg",
    imageType = "jpg",
    instructions = "",
    license = "CC BY-SA 3.0",
    lowFodmap = false,
    nutrition = RecipeDetailsResponse.Nutrition(
        caloricBreakdown = RecipeDetailsResponse.Nutrition.CaloricBreakdown(
            percentCarbs = 65.8,
            percentFat = 30.86,
            percentProtein = 3.34
        ),
        flavonoids = listOf(
            RecipeDetailsResponse.Nutrition.Flavonoid(amount = 0.0, name = "Apigenin", unit = "mg"),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Kaempferol",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Quercetin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Myricetin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(amount = 0.0, name = "Luteolin", unit = "mg"),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Eriocitrin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Hesperetin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Naringenin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Theaflavin",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Thearubigins",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Anthocyanins",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Catechins",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Epicatechins",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Flavanols",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Flavanones",
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.Flavonoid(
                amount = 0.0,
                name = "Isoflavones",
                unit = "mg"
            )
        ),
        ingredients = listOf(
            RecipeDetailsResponse.Nutrition.Ingredient(
                amount = 1.0,
                id = 1001,
                name = "butter",
                nutrients = listOf(
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 102.11,
                        name = "Calories",
                        percentOfDailyNeeds = 5.1,
                        unit = "kcal"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 11.52,
                        name = "Fat",
                        percentOfDailyNeeds = 17.72,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Saturated Fat",
                        percentOfDailyNeeds = 0.07,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.03,
                        name = "Monounsaturated Fat",
                        percentOfDailyNeeds = 0.0,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Polyunsaturated Fat",
                        percentOfDailyNeeds = 0.0,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 30.4,
                        name = "Cholesterol",
                        percentOfDailyNeeds = 10.13,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Sodium",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Potassium",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Carbohydrates",
                        percentOfDailyNeeds = 0.0,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Fiber",
                        percentOfDailyNeeds = 0.0,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Sugar",
                        percentOfDailyNeeds = 0.0,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.12,
                        name = "Protein",
                        percentOfDailyNeeds = 0.24,
                        unit = "g"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Vitamin A",
                        percentOfDailyNeeds = 0.0,
                        unit = "IU"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Vitamin C",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Vitamin D",
                        percentOfDailyNeeds = 0.0,
                        unit = "µg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Vitamin E",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Vitamin K",
                        percentOfDailyNeeds = 0.0,
                        unit = "µg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Calcium",
                        percentOfDailyNeeds = 0.04,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Iron",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Magnesium",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.1,
                        name = "Phosphorus",
                        percentOfDailyNeeds = 0.01,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.01,
                        name = "Zinc",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Copper",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Manganese",
                        percentOfDailyNeeds = 0.0,
                        unit = "mg"
                    ),
                    RecipeDetailsResponse.Nutrition.NutrientX(
                        amount = 0.0,
                        name = "Selenium",
                        percentOfDailyNeeds = 0.0,
                        unit = "µg"
                    )
                ),
                unit = "tbsp"
            )
        ),
        nutrients = listOf(
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 584.49,
                name = "Calories",
                percentOfDailyNeeds = 29.22,
                unit = "kcal"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 20.18,
                name = "Fat",
                percentOfDailyNeeds = 31.05,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 7.71,
                name = "Saturated Fat",
                percentOfDailyNeeds = 48.21,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 8.13,
                name = "Monounsaturated Fat",
                percentOfDailyNeeds = 0.0,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 2.31,
                name = "Polyunsaturated Fat",
                percentOfDailyNeeds = 0.0,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 46.99,
                name = "Cholesterol",
                percentOfDailyNeeds = 15.66,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 365.32,
                name = "Sodium",
                percentOfDailyNeeds = 15.22,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 428.63, name = "Potassium",
                percentOfDailyNeeds = 12.25,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 85.64,
                name = "Carbohydrates",
                percentOfDailyNeeds = 28.55,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 7.1,
                name = "Fiber",
                percentOfDailyNeeds = 28.4,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 8.62,
                name = "Sugar",
                percentOfDailyNeeds = 9.58,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 19.03,
                name = "Protein",
                percentOfDailyNeeds = 38.06,
                unit = "g"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 132.14,
                name = "Vitamin A",
                percentOfDailyNeeds = 2.64,
                unit = "IU"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 39.1,
                name = "Vitamin C",
                percentOfDailyNeeds = 47.44,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 0.77,
                name = "Vitamin D",
                percentOfDailyNeeds = 5.13,
                unit = "µg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 2.92,
                name = "Vitamin E",
                percentOfDailyNeeds = 19.49,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 7.93,
                name = "Vitamin K",
                percentOfDailyNeeds = 6.61,
                unit = "µg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 252.19,
                name = "Calcium",
                percentOfDailyNeeds = 25.22,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 3.26,
                name = "Iron",
                percentOfDailyNeeds = 18.11,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 72.84,
                name = "Magnesium",
                percentOfDailyNeeds = 18.21,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 247.96,
                name = "Phosphorus",
                percentOfDailyNeeds = 24.8,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 2.16,
                name = "Zinc",
                percentOfDailyNeeds = 14.4,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 0.29,
                name = "Copper",
                percentOfDailyNeeds = 14.35,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 0.76,
                name = "Manganese",
                percentOfDailyNeeds = 38.13,
                unit = "mg"
            ),
            RecipeDetailsResponse.Nutrition.NutrientX(
                amount = 19.9,
                name = "Selenium",
                percentOfDailyNeeds = 28.43,
                unit = "µg"
            )
        ),
        properties = listOf(
            RecipeDetailsResponse.Nutrition.Property(
                amount = 45.0,
                name = "Preparation Time",
                unit = "minutes"
            ),
            RecipeDetailsResponse.Nutrition.Property(
                amount = 25.0,
                name = "Cook Time",
                unit = "minutes"
            ),
            RecipeDetailsResponse.Nutrition.Property(
                amount = 70.0,
                name = "Total Time",
                unit = "minutes"
            )
        ),
        weightPerServing = RecipeDetailsResponse.Nutrition.WeightPerServing(
            amount = 428,
            unit = "g"
        )
    ),
    occasions = emptyList(),
    originalId = "",
    preparationMinutes = 20,
    pricePerServing = 163.15,
    readyInMinutes = 45,
    servings = 2,
    sourceName = "Full Belly Sisters",
    sourceUrl = "http://fullbellysisters.blogspot.com/2012/06/pasta-with-garlic-scallions-cauliflower.html",
    spoonacularScore = 83.0,
    spoonacularSourceUrl = "https://spoonacular.com/pasta-with-garlic-scallions-cauliflower-breadcrumbs-716429",
    summary = "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs might be a good recipe to expand your main course repertoire. One portion of this dish contains approximately <b>19g of protein </b>,  <b>20g of fat </b>, and a total of  <b>584 calories </b>. For  <b>$1.63 per serving </b>, this recipe  <b>covers 23% </b> of your daily requirements of vitamins and minerals. This recipe serves 2. It is brought to you by fullbellysisters.blogspot.com. 209 people were glad they tried this recipe. A mixture of scallions, salt and pepper, white wine, and a handful of other ingredients are all it takes to make this recipe so scrumptious. From preparation to the plate, this recipe takes approximately  <b>45 minutes </b>. All things considered, we decided this recipe  <b>deserves a spoonacular score of 83% </b>. This score is awesome. If you like this recipe, take a look at these similar recipes: <a href=\"https://spoonacular.com/recipes/cauliflower-gratin-with-garlic-breadcrumbs-318375\">Cauliflower Gratin with Garlic Breadcrumbs</a>, < href=\"https://spoonacular.com/recipes/pasta-with-cauliflower-sausage-breadcrumbs-30437\">Pasta With Cauliflower, Sausage, & Breadcrumbs</a>, and <a href=\"https://spoonacular.com/recipes/pasta-with-roasted-cauliflower-parsley-and-breadcrumbs-30738\">Pasta With Roasted Cauliflower, Parsley, And Breadcrumbs</a>.",
    sustainable = false,
    title = "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs",
    vegan = false,
    vegetarian = false,
    veryHealthy = false,
    veryPopular = false,
    weightWatcherSmartPoints = 17,
    winePairing = RecipeDetailsResponse.WinePairing(
        pairedWines = listOf("chardonnay", "gruener veltliner", "sauvignon blanc"),
        pairingText = "Chardonnay, Gruener Veltliner, and Sauvignon Blanc are great choices for Pasta. Sauvignon Blanc and Gruner Veltliner both have herby notes that complement salads with enough acid to match tart vinaigrettes, while a Chardonnay can be a good pick for creamy salad dressings. The Buddha Kat Winery Chardonnay with a 4 out of 5 star rating seems like a good match. It costs about 25 dollars per bottle.",
        productMatches = listOf(
            RecipeDetailsResponse.WinePairing.ProductMatch(
                id = 469199,
                title = "Buddha Kat Winery Chardonnay",
                description = "We barrel ferment our Chardonnay and age it in a mix of Oak and Stainless. Giving this light bodied wine modest oak character, a delicate floral aroma, and a warming finish.",
                price = "\$25.0",
                imageUrl = "https://img.spoonacular.com/products/469199-312x231.jpg",
                averageRating = 0.8,
                ratingCount = 1.0,
                score = 0.55,
                link = "https://www.amazon.com/2015-Buddha-Kat-Winery-Chardonnay/dp/B00OSAVVM4?tag=spoonacular-20"
            )
        )
    )
)
