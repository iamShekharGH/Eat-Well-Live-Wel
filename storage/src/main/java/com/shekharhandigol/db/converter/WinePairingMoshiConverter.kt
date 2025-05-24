package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class WinePairingMoshiConverter(private val moshi: Moshi) {

    private val listType = Types.newParameterizedType(
        List::class.java,
        RecipeDetailsResponse.WinePairing.ProductMatch::class.java
    )

    private val jsonAdapter: JsonAdapter<RecipeDetailsResponse.WinePairing> by lazy {
        moshi.adapter(listType)
    }

    @TypeConverter
    fun fromString(value: String?): RecipeDetailsResponse.WinePairing? {
        return value?.let { jsonAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromWinePairing(winePairing: RecipeDetailsResponse.WinePairing?): String? {
        return winePairing?.let { jsonAdapter.toJson(it) }
    }
}