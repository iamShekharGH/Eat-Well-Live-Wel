package com.shekharhandigol.core.models.uiModels

data class RecipeDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val summary: String,
    val instructions: String,
    val extendedIngredients: List<IngredientItem>,
    val readyInMinutes: Int,
    val servings: Int,
    val healthScore: Double,
    val aggregateLikes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val dishTypes: List<String>,
    val diets: List<String>,
    val nutrition: RecipeNutrition?,
    val winePairing: WineSuggestion?
)

data class IngredientItem(
    val id: Int,
    val name: String,
    val originalString: String,
    val amount: Double,
    val unit: String,
    val imageUrl: String?
)

data class RecipeNutrition(
    val nutrients: List<NutrientInfo>,
    val caloricBreakdown: CaloricBreakdownInfo?,
    val weightPerServing: String
)

data class NutrientInfo(
    val name: String,
    val amount: Double,
    val unit: String,
    val percentOfDailyNeeds: Double
)

data class CaloricBreakdownInfo(
    val percentProtein: Double,
    val percentFat: Double,
    val percentCarbs: Double
)

data class WineSuggestion(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<WineProduct>
)

data class WineProduct(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val averageRating: Double,
    val link: String
)