package com.shekharhandigol.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailsDao {

    // --- Write Operations (suspend) ---

    /**
     * Inserts or updates a single recipe detail into the table.
     * If a recipe detail with the same `recipeId` (assuming it's unique or part of PK for upsert)
     * already exists, it will be replaced.
     * Your RecipeDetailTable has 'detailId' as PK and 'recipeId' as a unique indexed foreign key.
     * Upsert will use the PK (`detailId`) by default for conflict resolution.
     * If you intend to upsert based on `recipeId` and `recipeId` is NOT the PK,
     * this might not behave as "replace based on recipeId".
     * However, since `recipeId` is unique, an insert of a new detail for an existing `recipeId`
     * would likely violate the unique constraint unless handled by `onConflict` on the `recipeId` index,
     * or if you first query and then update.
     * Given your previous `@Insert(onConflict = OnConflictStrategy.REPLACE)`,
     * and `recipeId` being unique, an upsert should effectively replace based on `detailId`
     * or insert if new. If you need to replace based on `recipeId` where `detailId` might be different,
     * you'd need a custom transaction (delete by recipeId then insert).
     *
     * @param recipeDetail The [RecipeDetailTable] object to upsert.
     * @return The row ID of the inserted item if it was an insert, or an implementation-dependent
     * value for an update. Often, for upsert, focusing on the operation completing is key.
     * Room's @Upsert returning Long gives the rowId of the inserted row, or 0 if it was an update of an existing row.
     */
    @Upsert
    suspend fun upsertRecipeDetail(recipeDetail: RecipeDetailTable): Long

    /**
     * Inserts or updates multiple recipe details in the table.
     *
     * @param recipeDetails A list of [RecipeDetailTable] objects to upsert.
     */
    @Upsert
    suspend fun upsertRecipeDetails(recipeDetails: List<RecipeDetailTable>)

    /**
     * Updates an existing recipe detail in the table.
     * Note: Prefer `upsertRecipeDetail` if your logic is "insert or replace".
     * This is useful if you only want to perform an update and fail or do nothing if the item doesn't exist.
     *
     * @param recipeDetail The [RecipeDetailTable] object to update.
     * @return The number of rows updated. This should be 1 if the item was found and updated.
     */
    @Update
    suspend fun updateRecipeDetail(recipeDetail: RecipeDetailTable): Int

    /**
     * Deletes a specific recipe detail by its associated `recipeId`.
     *
     * @param recipeId The ID of the recipe whose details should be deleted.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun deleteRecipeDetailByRecipeId(recipeId: Int): Int

    /**
     * Deletes all recipe details from the table. Use with caution.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM $RECIPE_DETAILS_TABLE_NAME")
    suspend fun clearAllRecipeDetails(): Int


    // --- Read Operations ---

    /**
     * Retrieves a specific recipe detail by its associated `recipeId` as an observable Flow.
     * This allows observing changes to the recipe detail.
     *
     * @param recipeId The ID of the recipe for which details are requested.
     * @return A Flow emitting the [RecipeDetailTable] if found (or null), and subsequent updates.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    fun getRecipeDetailByRecipeIdFlow(recipeId: Int): Flow<RecipeDetailTable?>

    /**
     * Retrieves a specific recipe detail by its associated `recipeId`.
     * This is a suspend function for a one-time fetch.
     *
     * @param recipeId The ID of the recipe for which details are requested.
     * @return The [RecipeDetailTable] if found, otherwise null.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId")
    suspend fun getRecipeDetailByRecipeIdOnce(recipeId: Int): RecipeDetailTable?

    /**
     * Retrieves all recipe details currently stored as an observable Flow.
     * Useful for debugging or specific use cases, but typically you'd fetch by recipeId.
     *
     * @return A Flow emitting a list of all [RecipeDetailTable] objects.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME")
    fun getAllRecipeDetailsFlow(): Flow<List<RecipeDetailTable>>

    /**
     * Retrieves all recipe details currently stored for a one-time operation.
     *
     * @return A list of all [RecipeDetailTable] objects.
     */
    @Query("SELECT * FROM $RECIPE_DETAILS_TABLE_NAME")
    suspend fun getAllRecipeDetailsOnce(): List<RecipeDetailTable>


    // --- Existence Checks ---

    /**
     * Checks if a recipe detail exists for a given `recipeId` as an observable Flow.
     *
     * @param recipeId The ID of the recipe to check.
     * @return A Flow emitting true if details exist, false otherwise, and subsequent updates.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId LIMIT 1)")
    fun hasDetailsForRecipeFlow(recipeId: Int): Flow<Boolean>

    /**
     * Checks if a recipe detail exists for a given `recipeId` for a one-time operation.
     *
     * @param recipeId The ID of the recipe to check.
     * @return True if details exist, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM $RECIPE_DETAILS_TABLE_NAME WHERE recipeId = :recipeId LIMIT 1)")
    suspend fun hasDetailsForRecipeOnce(recipeId: Int): Boolean
}