package com.shekharhandigol

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeWithDetails
import com.shekharhandigol.db.RecipeDao
import com.shekharhandigol.db.RecipeDetailsDao
import com.shekharhandigol.db.RecipeTable
import com.shekharhandigol.repository.RoomDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDbRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeDetailsDao: RecipeDetailsDao
) : RoomDbRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipeDao.getAllRecipesOnce().map { it.toRecipeUiModel() }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDao.getRecipeByIdOnce(id)?.toRecipeUiModel()
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.upsertRecipe(recipe.toRecipeEntity())
    }

    override suspend fun insertRecipe(
        id: Int,
        title: String,
        description: String,
        imageUrl: String,
        tags: List<String>,
        favourite: Boolean
    ) {
        val recipeEntity = RecipeTable(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
            tags = tags,
            favourite = favourite
        )
        recipeDao.upsertRecipe(recipeEntity)
    }

    override suspend fun insertRecipes(recipes: List<Recipe>) {
        recipeDao.upsertRecipes(recipes.map { it.toRecipeEntity() })
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipe.id.let { recipeDao.deleteRecipeById(it) }
    }

    override suspend fun deleteRecipe(id: Int) {
        recipeDao.deleteRecipeById(id)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe.toRecipeEntity())
    }

    override suspend fun getRandomRecipe(): Recipe? {
        return recipeDao.getRandomRecipe()?.toRecipeUiModel()
    }

    override suspend fun updateFavourite(id: Int, favourite: Boolean) {
        recipeDao.updateFavouriteStatus(id, favourite)
    }

    override suspend fun getFavouriteRecipes(): List<Recipe> {
        return recipeDao.getFavouriteRecipesOnce().map { it.toRecipeUiModel() }
    }

    override suspend fun getRecipeDetailsByRecipeId(recipeId: Int): RecipeDetails? {
        return recipeDao.getRecipeWithDetailsByIdOnce(recipeId)?.details?.toRecipeDetailsUiModel()
    }

    override suspend fun insertRecipeDetails(recipeDetailsList: List<RecipeDetails>) {
        val entities = recipeDetailsList.mapNotNull { uiModel ->

            if (uiModel.id != 0 && uiModel.id != -1) {
                uiModel.toRecipeDetailEntity(associatedRecipeId = uiModel.id)
            } else {
                null
            }
        }
        if (entities.isNotEmpty() && entities.size == recipeDetailsList.size) {
            recipeDetailsDao.upsertRecipeDetails(entities)
        }
    }

    override suspend fun updateRecipeDetails(recipeDetail: RecipeDetails) {

        if (recipeDetail.id != 0 && recipeDetail.id != -1) {
            val entity = recipeDetail.toRecipeDetailEntity(associatedRecipeId = recipeDetail.id)
            recipeDetailsDao.updateRecipeDetail(entity)
        }
    }

    override suspend fun insertRecipeDetail(recipeDetail: RecipeDetails) {
        // Assuming recipeDetail.id IS the recipeId.
        if (recipeDetail.id != 0 && recipeDetail.id != -1) {
            val entity = recipeDetail.toRecipeDetailEntity(associatedRecipeId = recipeDetail.id)
            recipeDetailsDao.upsertRecipeDetail(entity)
        }
    }

    override suspend fun getRecipeWithDetailsById(recipeId: Int): RecipeWithDetails? {
        return recipeDao.getRecipeWithDetailsByIdOnce(recipeId)?.toUiModel()
    }

    override suspend fun getFavoriteRecipesWithDetails(): List<RecipeWithDetails> {
        return recipeDao.getFavoriteRecipesWithDetailsOnce().map { it.toUiModel() }
    }

    override fun getRecipeWithDetailsByIdFlow(recipeId: Int): Flow<RecipeWithDetails?> {
        return recipeDao.getRecipeWithDetailsByIdFlow(recipeId).map { dbResult ->
            dbResult?.toUiModel()
        }
    }

    override fun getFavoriteRecipesWithDetailsFlow(): Flow<List<RecipeWithDetails>> {
        return recipeDao.getFavoriteRecipesWithDetailsFlow().map { listDbResult ->
            listDbResult.map { it.toUiModel() }
        }
    }
}
