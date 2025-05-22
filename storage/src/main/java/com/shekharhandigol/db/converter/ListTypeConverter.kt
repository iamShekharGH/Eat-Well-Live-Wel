package com.shekharhandigol.db.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ListTypeConverter {

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory()).build()

    private val listOfStringAdapter = moshi.adapter<List<String>>(
        Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
    )

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { listOfStringAdapter.toJson(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let { listOfStringAdapter.fromJson(it) } ?: emptyList()
    }
}