package com.peep.nocalorieleftbehind.core.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

class ListOfLongConverter {
    @OptIn(ExperimentalStdlibApi::class)
    @TypeConverter
    fun convertToString(listOfLong: List<Long>): String? {
        return try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<List<Long>> = moshi.adapter<List<Long>>()
            return jsonAdapter.toJson(listOfLong)
        } catch (e: Exception) {
            null
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @TypeConverter
    fun convertListOfLong(json: String): List<Long>? {
        return try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<List<Long>> = moshi.adapter<List<Long>>()
            jsonAdapter.fromJson(json)
        } catch (e: Exception) {
            null
        }
    }
}