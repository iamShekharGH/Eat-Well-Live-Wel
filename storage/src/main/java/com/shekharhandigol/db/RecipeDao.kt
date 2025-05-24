package com.shekharhandigol.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    // --- Observable Read Operations (Flow) ---

    /**
     * Retrieves all recipes as an observable Flow.
     * Emits a new list whenever the RecipeTable data changes.
     */
    @Query("SELECT * FROM RecipeTable ORDER BY title ASC") // Added default ordering
    fun getAllRecipesFlow(): Flow<List<RecipeTable>>

    /**
     * Retrieves a specific recipe by its ID as an observable Flow.
     * Emits a new RecipeTable (or null) if the specific recipe data changes.
     */
    @Query("SELECT * FROM RecipeTable WHERE id = :id")
    fun getRecipeByIdFlow(id: Int): Flow<RecipeTable?>

    /**
     * Retrieves all favourite recipes as an observable Flow.
     */
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY title ASC")
    fun getFavouriteRecipesFlow(): Flow<List<RecipeTable>>

    /**
     * Searches recipes by title (case-insensitive) as an observable Flow.
     */
    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchRecipesFlow(query: String): Flow<List<RecipeTable>>

    /**
     * Retrieves recipes by a specific tag as an observable Flow.
     * Note: This uses LIKE which might be inefficient for large datasets if tags are not indexed well
     * or if the tags column stores a delimited string. Consider normalization for better performance.
     */
    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    fun getRecipesByTagFlow(tag: String): Flow<List<RecipeTable>>

    /**
     * Gets the total count of recipes as an observable Flow.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable")
    fun getRecipeCountFlow(): Flow<Int>

    /**
     * Gets the count of favourite recipes as an observable Flow.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable WHERE favourite = 1")
    fun getFavouriteRecipeCountFlow(): Flow<Int>

    /**
     * Gets the count of recipes matching a specific tag as an observable Flow.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    fun getRecipesByTagCountFlow(tag: String): Flow<Int>


    // --- One-Time Read Operations (suspend) ---

    /**
     * Retrieves all recipes for a one-time operation.
     */
    @Query("SELECT * FROM RecipeTable ORDER BY title ASC")
    suspend fun getAllRecipesOnce(): List<RecipeTable>

    /**
     * Retrieves a specific recipe by its ID for a one-time operation.
     * @return The [RecipeTable] if found, otherwise null.
     */
    @Query("SELECT * FROM RecipeTable WHERE id = :id")
    suspend fun getRecipeByIdOnce(id: Int): RecipeTable?

    /**
     * Retrieves all favourite recipes for a one-time operation.
     */
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY title ASC")
    suspend fun getFavouriteRecipesOnce(): List<RecipeTable>

    /**
     * Searches recipes by title (case-insensitive) for a one-time operation.
     */
    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY title ASC")
    suspend fun searchRecipesOnce(query: String): List<RecipeTable>

    /**
     * Retrieves recipes by a specific tag for a one-time operation.
     */
    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    suspend fun getRecipesByTagOnce(tag: String): List<RecipeTable>

    /**
     * Gets the total count of recipes for a one-time operation.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable")
    suspend fun getRecipeCountOnce(): Int

    /**
     * Gets the count of favourite recipes for a one-time operation.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavouriteRecipeCountOnce(): Int

    /**
     * Gets the count of recipes matching a specific tag for a one-time operation.
     */
    @Query("SELECT COUNT(*) FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getRecipesByTagCountOnce(tag: String): Int

    // --- Write Operations (suspend) ---

    /**
     * Inserts or updates a single recipe.
     * Replaces the recipe if a conflict occurs on the primary key.
     */
    @Upsert // Or @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecipe(recipe: RecipeTable)

    /**
     * Inserts or updates a list of recipes.
     * Replaces recipes if conflicts occur on their primary keys.
     */
    @Upsert // Or @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecipes(recipes: List<RecipeTable>)

    /**
     * Deletes a specific recipe.
     * @param recipe The recipe entity to delete.
     */
    @Delete
    suspend fun deleteRecipe(recipe: RecipeTable)

    /**
     * Deletes a specific recipe by its ID.
     * @param id The ID of the recipe to delete.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM RecipeTable WHERE id = :id")
    suspend fun deleteRecipeById(id: Int): Int // Returns Int for number of rows affected

    /**
     * Updates an existing recipe.
     * Consider using upsertRecipe for insert-or-update logic.
     * This method is useful if you specifically only want to update an existing record.
     */
    @Update
    suspend fun updateRecipe(recipe: RecipeTable)

    /**
     * Updates the 'favourite' status of a specific recipe.
     * @param id The ID of the recipe to update.
     * @param favourite The new favourite status.
     */
    @Query("UPDATE RecipeTable SET favourite = :favourite WHERE id = :id")
    suspend fun updateFavouriteStatus(id: Int, favourite: Boolean)


    // --- Random Recipe Operations (suspend, typically one-time fetches) ---

    @Query("SELECT * FROM RecipeTable ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipes(limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipe(): RecipeTable?

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipes(limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomFavouriteRecipe(): RecipeTable?

    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipesByTag(tag: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipeByTag(tag: String): RecipeTable?

    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipesByQuery(query: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipeByQuery(query: String): RecipeTable?

    // Disambiguated getRandomFavouriteRecipesByQueryAndTag
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipesByQueryAndTagWithLimit(
        query: String,
        tag: String,
        limit: Int
    ): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getSingleRandomFavouriteRecipeByQueryAndTag(
        query: String,
        tag: String
    ): RecipeTable?

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM()")
    suspend fun getAllRandomFavouriteRecipesByQueryAndTag(
        query: String,
        tag: String
    ): List<RecipeTable>


    // --- Transactional Operations for Related Data (RecipeWithDetails) ---

    /**
     * Retrieves a specific recipe along with its details (if they exist) by recipe ID.
     * Uses a Flow for observable queries.
     */
    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE id = :recipeId")
    fun getRecipeWithDetailsByIdFlow(recipeId: Int): Flow<RecipeWithDetails?>

    /**
     * Retrieves a specific recipe along with its details (if they exist) by recipe ID.
     * Suspend function for a one-time fetch.
     */
    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE id = :recipeId")
    suspend fun getRecipeWithDetailsByIdOnce(recipeId: Int): RecipeWithDetails? // Renamed for consistency

    /**
     * Retrieves all favorite recipes along with their details.
     * Uses a Flow for observable queries.
     */
    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    fun getFavoriteRecipesWithDetailsFlow(): Flow<List<RecipeWithDetails>>

    /**
     * Retrieves all favorite recipes along with their details.
     * Suspend function for a one-time fetch.
     */
    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavoriteRecipesWithDetailsOnce(): List<RecipeWithDetails> // Renamed for consistency
}