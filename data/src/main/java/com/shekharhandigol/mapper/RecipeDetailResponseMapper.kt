package com.shekharhandigol.mapper

import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.core.models.uiModels.CaloricBreakdownInfo
import com.shekharhandigol.core.models.uiModels.IngredientItem
import com.shekharhandigol.core.models.uiModels.NutrientInfo
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeNutrition
import com.shekharhandigol.core.models.uiModels.WineProduct
import com.shekharhandigol.core.models.uiModels.WineSuggestion

fun RecipeDetailsResponse.toDomain(): RecipeDetails {
    return RecipeDetails(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        summary = this.summary,
        instructions = this.instructions,
        extendedIngredients = this.extendedIngredients.mapNotNull { dtoIngredient ->

            if (dtoIngredient.name.isBlank() || dtoIngredient.id == -1) {
                null
            } else {
                IngredientItem(
                    id = dtoIngredient.id,
                    name = dtoIngredient.nameClean.ifBlank { dtoIngredient.name },
                    originalString = dtoIngredient.original,
                    amount = dtoIngredient.amount,
                    unit = dtoIngredient.unit,
                    imageUrl = dtoIngredient.image.takeIf { it.isNotBlank() }
                )
            }
        },
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        healthScore = this.healthScore,
        aggregateLikes = this.aggregateLikes,
        sourceName = this.sourceName,
        sourceUrl = this.sourceUrl,
        dishTypes = this.dishTypes,
        diets = this.diets.mapNotNull { it?.toString() },
        nutrition = this.nutrition.let { dtoNutrition ->
            // Only create RecipeNutrition if there are nutrients
            if (dtoNutrition.nutrients.isEmpty() && dtoNutrition.caloricBreakdown.percentCarbs == -1.0) {
                null
            } else {
                RecipeNutrition(
                    nutrients = dtoNutrition.nutrients.map { nutrientX ->
                        NutrientInfo(
                            name = nutrientX.name,
                            amount = nutrientX.amount,
                            unit = nutrientX.unit,
                            percentOfDailyNeeds = nutrientX.percentOfDailyNeeds
                        )
                    },
                    caloricBreakdown = dtoNutrition.caloricBreakdown.let { cb ->
                        if (cb.percentCarbs == -1.0 && cb.percentFat == -1.0 && cb.percentProtein == -1.0) {
                            null // Don't create if all values are default/invalid
                        } else {
                            CaloricBreakdownInfo(
                                percentProtein = cb.percentProtein,
                                percentFat = cb.percentFat,
                                percentCarbs = cb.percentCarbs
                            )
                        }
                    },
                    weightPerServing = "${dtoNutrition.weightPerServing.amount}${dtoNutrition.weightPerServing.unit}"
                )
            }
        },
        winePairing = this.winePairing.let { dtoWinePairing ->
            // Only create WineSuggestion if there's pairing text or products
            if (dtoWinePairing.pairingText.isBlank() && dtoWinePairing.productMatches.isEmpty()) {
                null
            } else {
                WineSuggestion(
                    pairedWines = dtoWinePairing.pairedWines,
                    pairingText = dtoWinePairing.pairingText,
                    productMatches = dtoWinePairing.productMatches.map { productMatch ->
                        WineProduct(
                            id = productMatch.id,
                            title = productMatch.title,
                            description = productMatch.description,
                            price = productMatch.price,
                            imageUrl = productMatch.imageUrl,
                            averageRating = productMatch.averageRating,
                            link = productMatch.link
                        )
                    }
                )
            }
        }
    )
}