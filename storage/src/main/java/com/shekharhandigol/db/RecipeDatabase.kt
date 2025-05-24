package com.shekharhandigol.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RecipeTable::class, RecipeDetailTable::class],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailDao(): RecipeDetailsDao

}