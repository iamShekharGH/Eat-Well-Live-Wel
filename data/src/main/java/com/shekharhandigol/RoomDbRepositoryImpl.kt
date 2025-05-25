package com.shekharhandigol

import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeWithDetails
import com.shekharhandigol.data.mapper.toRecipeDetailEntity
import com.shekharhandigol.data.mapper.toRecipeDetailsUiModel
import com.shekharhandigol.data.mapper.toRecipeEntity
import com.shekharhandigol.data.mapper.toRecipeUiModel
import com.shekharhandigol.data.mapper.toUiModel
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
        // For insertRecipeDetails, each RecipeDetails needs its associated recipeId.
        // This is tricky if the RoomDbRepository interface doesn't provide it.
        // Assuming RecipeDetails UI model has a non-nullable `id` that corresponds to `recipeId`.
        // If RecipeDetails.id is nullable or doesn't mean recipeId, this needs adjustment.
        // The toRecipeDetailEntity mapper needs the associatedRecipeId.
        val entities = recipeDetailsList.mapNotNull { uiModel ->
            // Assuming uiModel.id IS the recipeId for this detail
            // If RecipeDetails.id can be a detail-specific ID (not recipeId),
            // then this operation needs a different way to get the recipeId for each detail.
            // For now, let's assume uiModel.id is the recipeId.
            if (uiModel.id != 0 && uiModel.id != -1) { // Ensure id is valid for association
                uiModel.toRecipeDetailEntity(associatedRecipeId = uiModel.id)
            } else {
                null // Or throw error, or handle cases where recipeId is not in RecipeDetails UI model
            }
        }
        if (entities.isNotEmpty() && entities.size == recipeDetailsList.size) { // Ensure all were mapped
            recipeDetailsDao.upsertRecipeDetails(entities)
        }
    }

    override suspend fun updateRecipeDetails(recipeDetail: RecipeDetails) {
        // Assuming recipeDetail.id IS the recipeId needed for mapping.
        // If RecipeDetails.id is a detail-specific ID, this is problematic.
        // toRecipeDetailEntity requires the associatedRecipeId.
        if (recipeDetail.id != 0 && recipeDetail.id != -1) {
            val entity = recipeDetail.toRecipeDetailEntity(associatedRecipeId = recipeDetail.id)
            recipeDetailsDao.updateRecipeDetail(entity) // Assuming DAO takes RecipeDetailTable
        }
    }

    override suspend fun insertRecipeDetail(recipeDetail: RecipeDetails) {
        // Assuming recipeDetail.id IS the recipeId.
        if (recipeDetail.id != 0 && recipeDetail.id != -1) {
            val entity = recipeDetail.toRecipeDetailEntity(associatedRecipeId = recipeDetail.id)
            recipeDetailsDao.upsertRecipeDetail(entity) // Assuming DAO takes RecipeDetailTable
        }
    }

    // --- RecipeWithDetails Methods ---

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
