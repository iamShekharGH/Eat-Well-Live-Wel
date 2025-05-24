package com.shekharhandigol.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailDao {

    /**
     * Inserts a single recipe detail into the table.
     * If a recipe detail with the same `recipeId` already exists, it will be replaced.
     * Consider if you need a specific PK for RecipeDetailTable or if recipeId can be the PK.
     * My current RecipeDetailTable has 'detailId' as PK and 'recipeId' as a unique indexed foreign key.
     *
     * @param recipeDetail The [RecipeDetailTable] object to insert.
     * @return The row ID of the inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDetail(recipeDetail: RecipeDetailTable): Long

    /**
     * Inserts multiple recipe details into the table.
     * If any recipe detail with the same `recipeId` already exists, it will be replaced.
     *
     * @param recipeDetails A list of [RecipeDetailTable] objects to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDetails(recipeDetails: List<RecipeDetailTable>)

    /**
     * Updates an existing recipe detail in the table.
     *
     * @param recipeDetail The [RecipeDetailTable] object to update.
     * @return The number of rows updated. This should be 1 if the item was found and updated.
     */
    @Update
    suspend fun updateRecipeDetail(recipeDetail: RecipeDetailTable): Int

    /**
     * Retrieves a specific recipe detail by its associated `recipeId`.
     * This is a suspend function for a one-time fetch.
     *
     * @param recipeId The ID of the recipe for which details are requested.
     * @return The [RecipeDetailTable] if found, otherwise null.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun getRecipeDetailByRecipeId(recipeId: Int): RecipeDetailTable?

    /**
     * Retrieves a specific recipe detail by its associated `recipeId` as a Flow.
     * This allows observing changes to the recipe detail.
     *
     * @param recipeId The ID of the recipe for which details are requested.
     * @return A Flow emitting the [RecipeDetailTable] if found (or null), and subsequent updates.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    fun getRecipeDetailByRecipeIdFlow(recipeId: Int): Flow<RecipeDetailTable?>

    /**
     * Deletes a specific recipe detail by its associated `recipeId`.
     *
     * @param recipeId The ID of the recipe whose details should be deleted.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun deleteRecipeDetailByRecipeId(recipeId: Int): Int

    /**
     * Deletes all recipe details from the table.
     * Use with caution.
     */
    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME")
    suspend fun clearAllRecipeDetails()

    /**
     * Checks if a recipe detail exists for a given recipeId.
     *
     * @param recipeId The ID of the recipe to check.
     * @return True if details exist, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId LIMIT 1)")
    suspend fun hasDetailsForRecipe(recipeId: Int): Boolean

    /**
     * Retrieves all recipe details currently stored.
     * Useful for debugging or specific use cases, but typically you'd fetch by recipeId.
     *
     * @return A Flow emitting a list of all [RecipeDetailTable] objects.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME")
    fun getAllRecipeDetailsFlow(): Flow<List<RecipeDetailTable>>
}