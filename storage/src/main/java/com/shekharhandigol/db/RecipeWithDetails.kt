package com.shekharhandigol.db

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithDetails(
    @Embedded
    val recipe: RecipeTable,

    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId",
        entity = RecipeDetailTable::class
    )
    val details: RecipeDetailTable?
)