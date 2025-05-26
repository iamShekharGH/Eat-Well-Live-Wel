package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class NutritionMoshiConverter(private val moshi: Moshi) {

    private val jsonAdapter: JsonAdapter<RecipeDetailsResponse.Nutrition> by lazy {
        moshi.adapter(RecipeDetailsResponse.Nutrition::class.java)
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