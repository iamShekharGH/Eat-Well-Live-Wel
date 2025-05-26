package com.shekharhandigol.usecases

import com.shekharhandigol.UseCase
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.repository.SearchRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val searchRecipesRepository: SearchRecipesRepository
) : UseCase<Pair<String, String>, Flow<NetworkResult<List<Recipe>>>> {

    override suspend fun invoke(input: Pair<String, String>): Flow<NetworkResult<List<Recipe>>> {
        return searchRecipesRepository.getRecipes(
            apiKey = input.first,
            query = input.second
        )
    }
}

class GetRecipeDetailsUseCase @Inject constructor(
    private val searchRecipesRepository: SearchRecipesRepository
) : UseCase<Pair<String, Int>, Flow<NetworkResult<RecipeDetails>>> {

    override suspend fun invoke(input: Pair<String, Int>): Flow<NetworkResult<RecipeDetails>> {
        return searchRecipesRepository.getRecipeDetails(
            apiKey = input.first,
            id = input.second
        )
    }
}