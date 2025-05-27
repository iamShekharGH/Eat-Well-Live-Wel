package com.shekharhandigol.db

import android.content.Context
import androidx.room.Room
import com.shekharhandigol.db.converter.ExtendedIngredientListMoshiConverter
import com.shekharhandigol.db.converter.ListStringMoshiConverter
import com.shekharhandigol.db.converter.NutritionMoshiConverter
import com.shekharhandigol.db.converter.WinePairingMoshiConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
        extendedIngredientListMoshiConverter: ExtendedIngredientListMoshiConverter,
        nutritionMoshiConverter: NutritionMoshiConverter,
        winePairingMoshiConverter: WinePairingMoshiConverter,
        listStringMoshiConverter: ListStringMoshiConverter
    ): RecipeDatabase {
        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            ROOM_DATABASE_NAME
        ).addTypeConverter(extendedIngredientListMoshiConverter)
            .addTypeConverter(nutritionMoshiConverter)
            .addTypeConverter(winePairingMoshiConverter)
            .addTypeConverter(listStringMoshiConverter)
            .build()
    }

    @Provides
    fun providesRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao {
        return recipeDatabase.recipeDao()
    }

    @Provides
    fun providesRecipeDetailsDao(recipeDatabase: RecipeDatabase): RecipeDetailsDao {
        return recipeDatabase.recipeDetailDao()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideExtendedIngredientListMoshiConverter(moshi: Moshi): ExtendedIngredientListMoshiConverter {
        return ExtendedIngredientListMoshiConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideNutritionMoshiConverter(moshi: Moshi): NutritionMoshiConverter {
        return NutritionMoshiConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideWinePairingMoshiConverter(moshi: Moshi): WinePairingMoshiConverter {
        return WinePairingMoshiConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideListStringMoshiConverter(moshi: Moshi): ListStringMoshiConverter {
        return ListStringMoshiConverter(moshi)
    }


}