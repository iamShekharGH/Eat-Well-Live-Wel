package com.shekharhandigol.domain

import com.shekharhandigol.SearchRecipesRepo
import com.shekharhandigol.UseCase
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.core.models.searchRecepies.SearchRecipeResponse
import com.shekharhandigol.core.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val searchRecipesRepo: SearchRecipesRepo
) : UseCase<Pair<String, String>, Flow<NetworkResult<SearchRecipeResponse>>> {

    override suspend fun invoke(input: Pair<String, String>): Flow<NetworkResult<SearchRecipeResponse>> {
        return searchRecipesRepo.getRecipes(
            apiKey = input.first,
            query = input.second
        )
    }
}

class GetRecipeDetailsUseCase @Inject constructor(
    private val searchRecipesRepo: SearchRecipesRepo
) : UseCase<Pair<String, Int>, Flow<NetworkResult<RecipeDetailsResponse>>> {

    override suspend fun invoke(input: Pair<String, Int>): Flow<NetworkResult<RecipeDetailsResponse>> {
        return searchRecipesRepo.getRecipeDetails(
            apiKey = input.first,
            id = input.second
        )
    }
}