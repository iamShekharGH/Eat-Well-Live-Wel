package com.shekharhandigol.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shekharhandigol.db.converter.ListTypeConverter

const val RECIPE_TABLE_NAME = "RecipeTable"

@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipeTable(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val favourite: Boolean
)

@Database(
    entities = [RecipeTable::class],
    version = 1
)

@TypeConverters(ListTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}