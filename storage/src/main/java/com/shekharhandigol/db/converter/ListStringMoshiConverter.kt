package com.shekharhandigol.db.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class ListStringMoshiConverter(private val moshi: Moshi) {

    private val jsonAdapter by lazy {
        moshi.adapter<List<String>>(
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        )
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let { jsonAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.let { jsonAdapter.toJson(it) }
    }
}