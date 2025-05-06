package com.shekharhandigol

import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.core.models.searchRecepies.SearchRecipeResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(SpoonaclularApiInterface.BASE_URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): SpoonaclularApiInterface {
        return retrofit.create(SpoonaclularApiInterface::class.java)
    }


}

interface SpoonaclularApiInterface {
    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val BASE_URL_COMPLEX_SEARCH = "recipes/complexSearch"
    }

    @GET(BASE_URL_COMPLEX_SEARCH)
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): SearchRecipeResponse

    @GET
    suspend fun getRecipeById(): RecipeDetailsResponse

}


sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure(val code: Int, val message: String) : NetworkResult<Nothing>()
    data object NetworkError : NetworkResult<Nothing>()
}
