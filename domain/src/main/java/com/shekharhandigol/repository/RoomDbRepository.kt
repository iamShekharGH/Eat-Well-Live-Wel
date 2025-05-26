package com.shekharhandigol.repository

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeWithDetails
import kotlinx.coroutines.flow.Flow

interface RoomDbRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(id: Int): Recipe?
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun insertRecipe(
        id: Int,
        title: String,
        description: String,
        imageUrl: String,
        tags: List<String>,
        favourite: Boolean
    )

    suspend fun insertRecipes(recipes: List<Recipe>)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun deleteRecipe(id: Int)
    suspend fun updateRecipe(recipe: Recipe)
    suspend fun getRandomRecipe(): Recipe?
    suspend fun updateFavourite(id: Int, favourite: Boolean)
    suspend fun getFavouriteRecipes(): List<Recipe>
    suspend fun getRecipeDetailsByRecipeId(recipeId: Int): RecipeDetails?
    suspend fun insertRecipeDetails(recipeDetails: List<RecipeDetails>)
    suspend fun updateRecipeDetails(recipeDetail: RecipeDetails)
    suspend fun insertRecipeDetail(recipeDetail: RecipeDetails)
    suspend fun getRecipeWithDetailsById(recipeId: Int): RecipeWithDetails?
    suspend fun getFavoriteRecipesWithDetails(): List<RecipeWithDetails>
    fun getRecipeWithDetailsByIdFlow(recipeId: Int): Flow<RecipeWithDetails?>
    fun getFavoriteRecipesWithDetailsFlow(): Flow<List<RecipeWithDetails>>
}