package com.shekharhandigol

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.repository.MainRepository
import com.shekharhandigol.repository.RoomDbRepository
import com.shekharhandigol.repository.SearchRecipesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transformLatest
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val searchRecipesRepository: SearchRecipesRepository,
    private val roomDbRepository: RoomDbRepository
) : MainRepository {


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getRecipesStoreAndReturn(
        apiKey: String,
        query: String
    ): Flow<UiLoadState<List<Recipe>>> {
        return searchRecipesRepository.getRecipes(apiKey = apiKey, query = query)
            .transformLatest { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success<List<Recipe>> -> {
                        try {
                            roomDbRepository.insertRecipes(networkResult.data)
                            emitAll(
                                roomDbRepository.searchRecipes(query)
                                    .map<List<Recipe>, UiLoadState<List<Recipe>>> {
                                        UiLoadState.Success(it)
                                    }
                                    .catch {
                                        it.printStackTrace()
                                        emit(UiLoadState.Failure)
                                    }
                            )

                        } catch (e: Exception) {
                            e.printStackTrace()
                            emit(UiLoadState.Failure)
                        }

                    }

                    NetworkResult.Loading -> emit(UiLoadState.Loading)
                    is NetworkResult.Failure, NetworkResult.NetworkError -> {
                        try {
                            emitAll(
                                roomDbRepository.searchRecipes(query)
                                    .map<List<Recipe>, UiLoadState<List<Recipe>>> {
                                        UiLoadState.Success(it)
                                    }
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                            emit(UiLoadState.Failure)
                        }
                    }
                }

            }.onStart { emit(UiLoadState.Loading) }
            .catch { emit(UiLoadState.Failure) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getRecipeDetailsCheckIfFavAndStore(
        apiKey: String,
        id: Int
    ): Flow<UiLoadState<RecipeDetails>> {
        return searchRecipesRepository.getRecipeDetails(apiKey = apiKey, id = id)
            .transformLatest { networkResult ->
                when (networkResult) {
                    is NetworkResult.Failure, NetworkResult.NetworkError -> {
                        emit(UiLoadState.Failure)
                        try {
                            val recipeDetail =
                                roomDbRepository.getRecipeDetailsByRecipeId(id)
                            recipeDetail?.let {
                                emit(UiLoadState.Success(recipeDetail))
                            }
                            if (recipeDetail == null) {
                                emit(UiLoadState.Failure)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            emit(UiLoadState.Failure)
                        }
                    }

                    NetworkResult.Loading -> {
                        emit(UiLoadState.Loading)
                    }

                    is NetworkResult.Success<RecipeDetails> -> {
                        try {
                            val recipeDetails = networkResult.data
                            roomDbRepository.insertRecipeDetails(listOf(recipeDetails))
                            val recipeDetail =
                                roomDbRepository.getRecipeDetailsByRecipeId(recipeDetails.id)
                            recipeDetail?.let {
                                emit(UiLoadState.Success(recipeDetail))
                            }
                            if (recipeDetail == null) {
                                emit(UiLoadState.Failure)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            emit(UiLoadState.Failure)
                        }
                    }
                }
            }.onStart { emit(UiLoadState.Loading) }
            .catch { emit(UiLoadState.Failure) }
    }

    override suspend fun addItemToFav(id: Int, setToFav: Boolean) {
        roomDbRepository.updateFavourite(id = id, favourite = setToFav)
    }


}