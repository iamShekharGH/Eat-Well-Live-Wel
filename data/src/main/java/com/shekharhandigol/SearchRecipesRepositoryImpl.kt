package com.shekharhandigol


import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.mapper.toDomain
import com.shekharhandigol.mapper.toDomainList
import com.shekharhandigol.repository.SearchRecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRecipesRepositoryImpl @Inject constructor(
    private val apiInterface: SpoonacularApiInterface
) : SearchRecipesRepository {


    override fun getRecipes(
        apiKey: String,
        query: String,
    ): Flow<NetworkResult<List<Recipe>>> = flow<NetworkResult<List<Recipe>>> {
        val response = apiInterface.getRecipes(apiKey, query).toDomainList()
        emit(NetworkResult.Success(response))
    }.onStart { emit(NetworkResult.Loading) }
        .catch { e ->
            when (e) {
                is retrofit2.HttpException -> emit(
                    NetworkResult.Failure(
                        e.code(),
                        e.response()?.errorBody()?.string() ?: "HTTP error"
                    )
                )

                is IOException -> emit(NetworkResult.NetworkError)
                else -> emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
            }
        }

    override fun getRecipeDetails(apiKey: String, id: Int): Flow<NetworkResult<RecipeDetails>> =
        flow<NetworkResult<RecipeDetails>> {
            val response = apiInterface.getRecipeById(apiKey = apiKey, id = id)
            emit(NetworkResult.Success(response.toDomain()))
        }.onStart {
            emit(NetworkResult.Loading)
        }.catch { e ->
            when (e) {
                is retrofit2.HttpException -> emit(
                    NetworkResult.Failure(
                        e.code(),
                        e.response()?.errorBody()?.string() ?: "HTTP error"
                    )
                )

                is IOException -> emit(NetworkResult.NetworkError)
                else -> emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
            }
        }
}