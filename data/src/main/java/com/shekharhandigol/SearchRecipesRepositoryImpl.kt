package com.shekharhandigol


import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.mapper.toDomain
import com.shekharhandigol.mapper.toDomainList
import com.shekharhandigol.repository.SearchRecipesRepository
import kotlinx.coroutines.flow.flow
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
    ) = flow {
        emit(NetworkResult.Loading)
        try {
            val response = apiInterface.getRecipes(apiKey, query).toDomainList()
            emit(NetworkResult.Success(response))
        } catch (e: retrofit2.HttpException) {
            emit(
                NetworkResult.Failure(
                    e.code(),
                    e.response()?.errorBody()?.string() ?: "HTTP error"
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            emit(NetworkResult.NetworkError)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
        }
    }

    override fun getRecipeDetails(apiKey: String, id: Int) = flow {
        emit(NetworkResult.Loading)
        try {
            val response = apiInterface.getRecipeById(apiKey = apiKey, id = id)
            emit(NetworkResult.Success(response.toDomain()))
        } catch (e: retrofit2.HttpException) {
            emit(
                NetworkResult.Failure(
                    e.code(),
                    e.response()?.errorBody()?.string() ?: "HTTP error"
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            emit(NetworkResult.NetworkError)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
        }
    }

}