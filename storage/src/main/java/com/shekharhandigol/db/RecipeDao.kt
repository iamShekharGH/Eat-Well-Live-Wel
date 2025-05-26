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

    @Query("SELECT * FROM RecipeTable ORDER BY title ASC")
    fun getAllRecipesFlow(): Flow<List<RecipeTable>>


    @Query("SELECT * FROM RecipeTable WHERE id = :id")
    fun getRecipeByIdFlow(id: Int): Flow<RecipeTable?>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY title ASC")
    fun getFavouriteRecipesFlow(): Flow<List<RecipeTable>>


    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchRecipesFlow(query: String): Flow<List<RecipeTable>>


    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    fun getRecipesByTagFlow(tag: String): Flow<List<RecipeTable>>


    @Query("SELECT COUNT(*) FROM RecipeTable")
    fun getRecipeCountFlow(): Flow<Int>


    @Query("SELECT COUNT(*) FROM RecipeTable WHERE favourite = 1")
    fun getFavouriteRecipeCountFlow(): Flow<Int>


    @Query("SELECT COUNT(*) FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    fun getRecipesByTagCountFlow(tag: String): Flow<Int>


    @Query("SELECT * FROM RecipeTable ORDER BY title ASC")
    suspend fun getAllRecipesOnce(): List<RecipeTable>


    @Query("SELECT * FROM RecipeTable WHERE id = :id")
    suspend fun getRecipeByIdOnce(id: Int): RecipeTable?


    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY title ASC")
    suspend fun getFavouriteRecipesOnce(): List<RecipeTable>


    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY title ASC")
    suspend fun searchRecipesOnce(query: String): List<RecipeTable>


    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY title ASC")
    suspend fun getRecipesByTagOnce(tag: String): List<RecipeTable>


    @Query("SELECT COUNT(*) FROM RecipeTable")
    suspend fun getRecipeCountOnce(): Int


    @Query("SELECT COUNT(*) FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavouriteRecipeCountOnce(): Int


    @Query("SELECT COUNT(*) FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getRecipesByTagCountOnce(tag: String): Int


    @Upsert // Or @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecipe(recipe: RecipeTable)


    @Upsert // Or @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecipes(recipes: List<RecipeTable>)


    @Delete
    suspend fun deleteRecipe(recipe: RecipeTable)


    @Query("DELETE FROM RecipeTable WHERE id = :id")
    suspend fun deleteRecipeById(id: Int): Int // Returns Int for number of rows affected


    @Update
    suspend fun updateRecipe(recipe: RecipeTable)


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


    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE id = :recipeId")
    fun getRecipeWithDetailsByIdFlow(recipeId: Int): Flow<RecipeWithDetails?>


    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE id = :recipeId")
    suspend fun getRecipeWithDetailsByIdOnce(recipeId: Int): RecipeWithDetails? // Renamed for consistency


    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    fun getFavoriteRecipesWithDetailsFlow(): Flow<List<RecipeWithDetails>>


    @Transaction
    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavoriteRecipesWithDetailsOnce(): List<RecipeWithDetails> // Renamed for consistency
}