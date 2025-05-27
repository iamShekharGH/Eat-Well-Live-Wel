package com.shekharhandigol.repository

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.UiLoadState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getRecipesStoreAndReturn(
        apiKey: String,
        query: String
    ): Flow<UiLoadState<List<Recipe>>>

    suspend fun getRecipeDetailsCheckIfFavAndStore(
        apiKey: String,
        id: Int
    ): Flow<UiLoadState<RecipeDetails>>

    suspend fun addItemToFav(id: Int)

}