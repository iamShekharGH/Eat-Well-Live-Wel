package com.shekharhandigol.repository

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SearchRecipesRepository {
    fun getRecipes(apiKey: String, query: String): Flow<NetworkResult<List<Recipe>>>
    fun getRecipeDetails(apiKey: String, id: Int): Flow<NetworkResult<RecipeDetails>>
}