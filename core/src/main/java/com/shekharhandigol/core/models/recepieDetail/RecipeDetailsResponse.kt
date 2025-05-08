package com.shekharhandigol.core.models.recepieDetail

data class RecipeDetailsResponse(
    val aggregateLikes: Int = -1,
    val analyzedInstructions: List<Any?> = emptyList(),
    val cheap: Boolean = false,
    val cookingMinutes: Int = -1,
    val creditsText: String = "",
    val cuisines: List<Any?> = emptyList(),
    val dairyFree: Boolean = false,
    val diets: List<Any?> = emptyList(),
    val dishTypes: List<String> = emptyList(),
    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    val gaps: String = "",
    val glutenFree: Boolean = false,
    val healthScore: Double = -1.0,
    val id: Int = -1,
    val image: String = "",
    val imageType: String = "",
    val instructions: String = "",
    val license: String = "",
    val lowFodmap: Boolean = false,
    val nutrition: Nutrition = Nutrition(),
    val occasions: List<Any?> = emptyList(),
    val originalId: Any? = null,
    val preparationMinutes: Int = -1,
    val pricePerServing: Double = -1.0,
    val readyInMinutes: Int = -1,
    val servings: Int = -1,
    val sourceName: String = "",
    val sourceUrl: String = "",
    val spoonacularScore: Double = -1.0,
    val spoonacularSourceUrl: String = "",
    val summary: String = "",
    val sustainable: Boolean = false,
    val title: String = "",
    val vegan: Boolean = false,
    val vegetarian: Boolean = false,
    val veryHealthy: Boolean = false,
    val veryPopular: Boolean = false,
    val weightWatcherSmartPoints: Int = -1,
    val winePairing: WinePairing = WinePairing()
) {
    data class ExtendedIngredient(
        val aisle: String = "",
        val amount: Double = -1.0,
        val consistency: String = "",
        val id: Int = -1,
        val image: String = "",
        val measures: Measures = Measures(),
        val meta: List<String> = emptyList(),
        val name: String = "",
        val nameClean: String = "",
        val original: String = "",
        val originalName: String = "",
        val unit: String = ""
    ) {

        data class Measures(
            val metric: Metric = Metric(),
            val us: Us = Us()
        ) {

            data class Metric(
                val amount: Double = -1.0,
                val unitLong: String = "",
                val unitShort: String = ""
            )

            data class Us(
                val amount: Double = -1.0,
                val unitLong: String = "",
                val unitShort: String = ""
            )
        }
    }

    data class Nutrition(
        val caloricBreakdown: CaloricBreakdown = CaloricBreakdown(),
        val flavonoids: List<Flavonoid> = emptyList(),
        val ingredients: List<Ingredient> = emptyList(),
        val nutrients: List<NutrientX> = emptyList(),
        val properties: List<Property> = emptyList(),
        val weightPerServing: WeightPerServing = WeightPerServing()
    ) {
        data class CaloricBreakdown(
            val percentCarbs: Double = -1.0,
            val percentFat: Double = -1.0,
            val percentProtein: Double = -1.0
        )

        data class Flavonoid(
            val amount: Double = -1.0,
            val name: String = "",
            val unit: String = ""
        )

        data class Property(
            val amount: Double = -1.0,
            val name: String = "",
            val unit: String = ""
        )

        data class Ingredient(
            val amount: Double = -1.0,
            val id: Int = -1,
            val name: String = "",
            val nutrients: List<NutrientX> = emptyList(),
            val unit: String = ""
        )

        data class NutrientX(
            val amount: Double = -1.0,
            val name: String = "",
            val percentOfDailyNeeds: Double = -1.0,
            val unit: String = ""
        )

        data class WeightPerServing(
            val amount: Int = -1,
            val unit: String = ""
        )

    }

    data class WinePairing(
        val pairedWines: List<String> = emptyList(),
        val pairingText: String = "",
        val productMatches: List<ProductMatch> = emptyList()
    ){
        data class ProductMatch(
            val id: Int = -1,
            val title: String = "",
            val description: String = "",
            val price: String = "",
            val imageUrl: String = "",
            val averageRating: Double = -1.0,
            val ratingCount: Double = -1.0,
            val score: Double = -1.0,
            val link: String = ""
        )
    }
}