package com.shekharhandigol.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailsDao {

    // --- Write Operations (suspend) ---

    @Upsert
    suspend fun upsertRecipeDetail(recipeDetail: RecipeDetailTable): Long

    @Upsert
    suspend fun upsertRecipeDetails(recipeDetails: List<RecipeDetailTable>)

    @Update
    suspend fun updateRecipeDetail(recipeDetail: RecipeDetailTable): Int

    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun deleteRecipeDetailByRecipeId(recipeId: Int): Int

    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME")
    suspend fun clearAllRecipeDetails(): Int


    // --- Read Operations ---

    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    fun getRecipeDetailByRecipeIdFlow(recipeId: Int): Flow<RecipeDetailTable?>

    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun getRecipeDetailByRecipeIdOnce(recipeId: Int): RecipeDetailTable?

    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME")
    fun getAllRecipeDetailsFlow(): Flow<List<RecipeDetailTable>>

    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME")
    suspend fun getAllRecipeDetailsOnce(): List<RecipeDetailTable>


    // --- Existence Checks ---

    @Query("SELECT EXISTS(SELECT 1 FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId LIMIT 1)")
    fun hasDetailsForRecipeFlow(recipeId: Int): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId LIMIT 1)")
    suspend fun hasDetailsForRecipeOnce(recipeId: Int): Boolean
}