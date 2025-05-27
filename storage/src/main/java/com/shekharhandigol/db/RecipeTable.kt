package com.shekharhandigol.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.db.converter.ExtendedIngredientListMoshiConverter
import com.shekharhandigol.db.converter.ListStringMoshiConverter
import com.shekharhandigol.db.converter.NutritionMoshiConverter
import com.shekharhandigol.db.converter.WinePairingMoshiConverter

@TypeConverters(
    ListStringMoshiConverter::class
)
@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipeTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val favourite: Boolean
)

@TypeConverters(
    ExtendedIngredientListMoshiConverter::class,
    ListStringMoshiConverter::class,
    WinePairingMoshiConverter::class,
    NutritionMoshiConverter::class
)
@Entity(
    tableName = RECIPE_DETAILS_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RecipeTable::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["recipeId"], unique = true)]
)
data class RecipeDetailTable(
    @PrimaryKey(autoGenerate = true)
    val detailId: Long = 0,
    val recipeId: Int,

    val aggregateLikes: Int,
    val cookingMinutes: Int,
    val instructions: String,
    val summary: String,
    val healthScore: Double,
    val servings: Int,
    val sourceUrl: String,

    val dishTypes: List<String>,

    val extendedIngredients: List<RecipeDetailsResponse.ExtendedIngredient>?,
    val nutrition: RecipeDetailsResponse.Nutrition?,
    val winePairing: RecipeDetailsResponse.WinePairing?
)
