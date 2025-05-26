package com.shekharhandigol

import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.core.models.uiModels.CaloricBreakdownInfo
import com.shekharhandigol.core.models.uiModels.IngredientItem
import com.shekharhandigol.core.models.uiModels.NutrientInfo
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeNutrition
import com.shekharhandigol.core.models.uiModels.WineProduct
import com.shekharhandigol.core.models.uiModels.WineSuggestion
import com.shekharhandigol.db.RecipeDetailTable
import com.shekharhandigol.db.RecipeTable
import com.shekharhandigol.core.models.uiModels.RecipeWithDetails as UiRecipeWithDetails
import com.shekharhandigol.db.RecipeWithDetails as DbRecipeWithDetails


fun RecipeTable.toRecipeUiModel(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        tags = this.tags,
        favourite = this.favourite,
        imageType = ""
    )
}

fun Recipe.toRecipeEntity(): RecipeTable {
    return RecipeTable(
        id = if (this.id == -1) 0 else this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        tags = this.tags,
        favourite = this.favourite
    )
}


fun RecipeDetailTable.toRecipeDetailsUiModel(): RecipeDetails {

    fun RecipeDetailsResponse.ExtendedIngredient.toUiIngredientItem(): IngredientItem {
        return IngredientItem(
            id = this.id,
            name = this.name,
            originalString = this.original,
            amount = this.amount,
            unit = this.unit,
            imageUrl = this.image
        )
    }

    fun RecipeDetailsResponse.Nutrition.toUiRecipeNutrition(): RecipeNutrition {
        fun RecipeDetailsResponse.Nutrition.NutrientX.toUiNutrientInfo(): NutrientInfo {
            return NutrientInfo(
                name = this.name,
                amount = this.amount,
                unit = this.unit,
                percentOfDailyNeeds = this.percentOfDailyNeeds
            )
        }

        fun RecipeDetailsResponse.Nutrition.CaloricBreakdown.toUiCaloricBreakdownInfo(): CaloricBreakdownInfo {
            return CaloricBreakdownInfo(
                percentProtein = this.percentProtein,
                percentFat = this.percentFat,
                percentCarbs = this.percentCarbs
            )
        }

        return RecipeNutrition(
            nutrients = this.nutrients.map { it.toUiNutrientInfo() },
            caloricBreakdown = this.caloricBreakdown.toUiCaloricBreakdownInfo(),
            weightPerServing = "${this.weightPerServing.amount} ${this.weightPerServing.unit}".trim()
        )
    }

    fun RecipeDetailsResponse.WinePairing.toUiWineSuggestion(): WineSuggestion {
        fun RecipeDetailsResponse.WinePairing.ProductMatch.toUiWineProduct(): WineProduct {
            return WineProduct(
                id = this.id,
                title = this.title,
                description = this.description,
                price = this.price,
                imageUrl = this.imageUrl,
                averageRating = this.averageRating,
                link = this.link
            )
        }
        return WineSuggestion(
            pairedWines = this.pairedWines,
            pairingText = this.pairingText,
            productMatches = this.productMatches.map { it.toUiWineProduct() }
        )
    }

    return RecipeDetails(
        id = this.recipeId,
        title = "",
        imageUrl = "",
        summary = this.summary,
        instructions = this.instructions,
        extendedIngredients = this.extendedIngredients?.map { it.toUiIngredientItem() }
            ?: emptyList(),
        readyInMinutes = this.cookingMinutes,
        servings = this.servings,
        healthScore = this.healthScore,
        aggregateLikes = this.aggregateLikes,
        sourceName = "",
        sourceUrl = this.sourceUrl,
        dishTypes = this.dishTypes,
        diets = emptyList(),
        nutrition = this.nutrition?.toUiRecipeNutrition(),
        winePairing = this.winePairing?.toUiWineSuggestion()
    )
}

fun RecipeDetails.toRecipeDetailEntity(associatedRecipeId: Int): RecipeDetailTable {

    fun IngredientItem.toResponseEntity(): RecipeDetailsResponse.ExtendedIngredient {
        return RecipeDetailsResponse.ExtendedIngredient(
            id = this.id,
            name = this.name,
            original = this.originalString,
            amount = this.amount,
            unit = this.unit,
            image = this.imageUrl ?: "",
            aisle = "",
            consistency = "",
            measures = RecipeDetailsResponse.ExtendedIngredient.Measures(),
            meta = emptyList(),
            nameClean = this.name,
            originalName = this.originalString
        )
    }

    fun RecipeNutrition.toResponseEntity(): RecipeDetailsResponse.Nutrition {
        fun NutrientInfo.toResponseEntityNutrientX(): RecipeDetailsResponse.Nutrition.NutrientX {
            return RecipeDetailsResponse.Nutrition.NutrientX(
                name = this.name,
                amount = this.amount,
                unit = this.unit,
                percentOfDailyNeeds = this.percentOfDailyNeeds
            )
        }

        fun CaloricBreakdownInfo.toResponseEntity(): RecipeDetailsResponse.Nutrition.CaloricBreakdown {
            return RecipeDetailsResponse.Nutrition.CaloricBreakdown(
                percentProtein = this.percentProtein,
                percentFat = this.percentFat,
                percentCarbs = this.percentCarbs
            )
        }

        val parts = this.weightPerServing.trim().split(" ", limit = 2)
        val amountInt = parts.getOrNull(0)?.toIntOrNull() ?: -1
        val unitString = parts.getOrNull(1) ?: ""

        return RecipeDetailsResponse.Nutrition(
            nutrients = this.nutrients.map { it.toResponseEntityNutrientX() },
            caloricBreakdown = this.caloricBreakdown?.toResponseEntity()
                ?: RecipeDetailsResponse.Nutrition.CaloricBreakdown(),
            weightPerServing = RecipeDetailsResponse.Nutrition.WeightPerServing(
                amount = amountInt,
                unit = unitString
            ),
            flavonoids = emptyList(),
            ingredients = emptyList(),
            properties = emptyList()
        )
    }

    fun WineSuggestion.toResponseEntity(): RecipeDetailsResponse.WinePairing {
        fun WineProduct.toResponseEntityProductMatch(): RecipeDetailsResponse.WinePairing.ProductMatch {
            return RecipeDetailsResponse.WinePairing.ProductMatch(
                id = this.id,
                title = this.title,
                description = this.description,
                price = this.price,
                imageUrl = this.imageUrl,
                averageRating = this.averageRating,
                link = this.link,
                ratingCount = -1.0, // Default
                score = -1.0 // Default
            )
        }
        return RecipeDetailsResponse.WinePairing(
            pairedWines = this.pairedWines,
            pairingText = this.pairingText,
            productMatches = this.productMatches.map { it.toResponseEntityProductMatch() }
        )
    }

    return RecipeDetailTable(
        detailId = 0,
        recipeId = associatedRecipeId,
        aggregateLikes = this.aggregateLikes,
        cookingMinutes = this.readyInMinutes,
        instructions = this.instructions,
        summary = this.summary,
        healthScore = this.healthScore,
        servings = this.servings,
        sourceUrl = this.sourceUrl,
        dishTypes = this.dishTypes,
        extendedIngredients = this.extendedIngredients.map { it.toResponseEntity() },
        nutrition = this.nutrition?.toResponseEntity(),
        winePairing = this.winePairing?.toResponseEntity()
    )
}


fun DbRecipeWithDetails.toUiModel(): UiRecipeWithDetails {
    val recipeUi = this.recipe.toRecipeUiModel()
    val detailsUi = this.details?.toRecipeDetailsUiModel()?.copy(
        title = recipeUi.title,
        imageUrl = recipeUi.imageUrl,
        sourceName = recipeUi.title,
        diets = emptyList()
    )

    return UiRecipeWithDetails(
        recipe = recipeUi,
        details = detailsUi
    )
}


fun RecipeDetailsResponse.toEntity(recipeIdToAssociate: Int): RecipeDetailTable {
    return RecipeDetailTable(
        detailId = 0,
        recipeId = recipeIdToAssociate,
        aggregateLikes = this.aggregateLikes,
        cookingMinutes = this.cookingMinutes,
        instructions = this.instructions,
        summary = this.summary,
        healthScore = this.healthScore,
        servings = this.servings,
        sourceUrl = this.sourceUrl,
        dishTypes = this.dishTypes,
        extendedIngredients = this.extendedIngredients,
        nutrition = this.nutrition,
        winePairing = this.winePairing
    )
}