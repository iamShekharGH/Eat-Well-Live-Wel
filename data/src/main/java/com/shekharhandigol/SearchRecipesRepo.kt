package com.shekharhandigol

import com.shekharhandigol.core.network.NetworkResult
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchRecipesRepo @Inject constructor(
    private val apiInterface: SpoonaclularApiInterface
) {

    fun getRecipes(apiKey: String, query: String) = flow {
        emit(NetworkResult.Loading)
        try {
            val response = apiInterface.getRecipes(apiKey, query)
            emit(NetworkResult.Success(response))
        } catch (e: retrofit2.HttpException) {
            emit(
                NetworkResult.Failure(
                    e.code(),
                    e.response()?.errorBody()?.string() ?: "HTTP error"
                )
            )
        } catch (e: IOException) {
            emit(NetworkResult.NetworkError)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
        }
    }

    fun getRecipeDetails(apiKey: String, id: Int) = flow {
        emit(NetworkResult.Loading)
        try {
            val response = apiInterface.getRecipeById(apiKey = apiKey, id = id)
            emit(NetworkResult.Success(response))
        } catch (e: retrofit2.HttpException) {
            emit(
                NetworkResult.Failure(
                    e.code(),
                    e.response()?.errorBody()?.string() ?: "HTTP error"
                )
            )
        } catch (e: IOException) {
            emit(NetworkResult.NetworkError)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResult.Failure(-1, "An unexpected error occurred"))
        }
    }

}