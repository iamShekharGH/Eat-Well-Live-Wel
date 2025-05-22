package com.shekharhandigol.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecipeTable")
    suspend fun getAllRecipes(): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeTable

    @Query("INSERT INTO RecipeTable (id, title, description, imageUrl, tags, favourite) VALUES (:id, :title, :description, :imageUrl, :tags, :favourite)")
    suspend fun insertRecipe(
        id: Int, title: String, description: String, imageUrl: String,
        tags: List<String>, favourite: Boolean
    )

    @Insert
    suspend fun insertRecipes(recipes: List<RecipeTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeTable)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeTable)

    @Update
    suspend fun updateRecipe(recipe: RecipeTable)

    @Query("UPDATE RecipeTable SET favourite = :favourite WHERE id = :id")
    suspend fun updateFavourite(id: Int, favourite: Boolean)

    @Query("DELETE FROM RecipeTable WHERE id = :id")
    suspend fun deleteRecipe(id: Int)

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavouriteRecipes(): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%'")
    suspend fun searchRecipes(query: String): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getRecipesByTag(tag: String): List<RecipeTable>

    @Query("SELECT COUNT(*) FROM RecipeTable")
    suspend fun getRecipeCount(): Int

    @Query("SELECT COUNT(*) FROM RecipeTable WHERE favourite = 1")
    suspend fun getFavouriteRecipeCount(): Int

    @Query("SELECT COUNT(*) FROM RecipeTable WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getRecipesByTagCount(tag: String): Int

    @Query("SELECT * FROM RecipeTable ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipes(limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipe(): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipes(limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomFavouriteRecipe(): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipesByTag(tag: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipeByTag(tag: String): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomRecipesByQuery(query: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipeByQuery(query: String): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipesByQuery(query: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomFavouriteRecipeByQuery(query: String): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipesByTag(tag: String, limit: Int): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomFavouriteRecipeByTag(tag: String): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomFavouriteRecipesByQueryAndTag(
        query: String,
        tag: String,
        limit: Int
    ): List<RecipeTable>

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomFavouriteRecipeByQueryAndTag(query: String, tag: String): RecipeTable

    @Query("SELECT * FROM RecipeTable WHERE favourite = 1 AND title LIKE '%' || :query || '%' AND tags LIKE '%' || :tag || '%' ORDER BY RANDOM()")
    suspend fun getRandomFavouriteRecipesByQueryAndTag(
        query: String,
        tag: String
    ): List<RecipeTable>

}
