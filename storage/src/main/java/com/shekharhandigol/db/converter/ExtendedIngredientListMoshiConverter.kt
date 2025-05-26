package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class ExtendedIngredientListMoshiConverter(private val moshi: Moshi) {


    private val listType = Types.newParameterizedType(
        List::class.java,
        RecipeDetailsResponse.ExtendedIngredient::class.java
    )
    private val jsonAdapter: JsonAdapter<List<RecipeDetailsResponse.ExtendedIngredient>> by lazy {
        moshi.adapter(listType)
    }

    @TypeConverter
    fun fromString(value: String?): List<RecipeDetailsResponse.ExtendedIngredient>? {
        return value?.let { jsonAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromList(list: List<RecipeDetailsResponse.ExtendedIngredient>?): String? {
        return list?.let { jsonAdapter.toJson(it) }
    }
}