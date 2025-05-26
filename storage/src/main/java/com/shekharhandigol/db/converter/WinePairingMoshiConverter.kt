package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class WinePairingMoshiConverter(private val moshi: Moshi) {


    private val jsonAdapter: JsonAdapter<RecipeDetailsResponse.WinePairing> by lazy {
        moshi.adapter(RecipeDetailsResponse.WinePairing::class.java)
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