package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class NutritionMoshiConverter(private val moshi: Moshi) {

    private val listType = Types.newParameterizedType(
        List::class.java,
        RecipeDetailsResponse.Nutrition::class.java
    )

    private val jsonAdapter: JsonAdapter<RecipeDetailsResponse.Nutrition> by lazy {
        moshi.adapter(listType)
    }

    @TypeConverter
    fun fromJson(json: String?): RecipeDetailsResponse.Nutrition? {
        return if (json.isNullOrEmpty()) null else jsonAdapter.fromJson(json)
    }

    @TypeConverter
    fun toJson(nutrition: RecipeDetailsResponse.Nutrition?): String? {
        return if (nutrition == null) null else jsonAdapter.toJson(nutrition)
    }
}