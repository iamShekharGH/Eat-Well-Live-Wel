package com.shekharhandigol.usecases

import com.shekharhandigol.NoOutputUseCase
import com.shekharhandigol.UseCase
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : UseCase<Pair<String, String>, Flow<UiLoadState<List<Recipe>>>> {

    override suspend fun invoke(input: Pair<String, String>): Flow<UiLoadState<List<Recipe>>> {
        return mainRepository.getRecipesStoreAndReturn(
            apiKey = input.first,
            query = input.second
        )
    }
}

class GetRecipeDetailsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : UseCase<Pair<String, Int>, Flow<UiLoadState<RecipeDetails>>> {

    override suspend fun invoke(input: Pair<String, Int>): Flow<UiLoadState<RecipeDetails>> {

        return mainRepository.getRecipeDetailsCheckIfFavAndStore(
            apiKey = input.first,
            id = input.second
        )
    }
}

class SetItemToFavUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : NoOutputUseCase<Pair<Int, Boolean>> {

    override suspend fun invoke(input: Pair<Int, Boolean>) {
        mainRepository.addItemToFav(input.first, input.second)
    }
}