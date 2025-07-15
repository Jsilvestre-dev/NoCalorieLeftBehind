package com.peep.nocalorieleftbehind.core.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MacroNutrientConverter {
//    @OptIn(ExperimentalStdlibApi::class)
//    @TypeConverter
//    fun convertToString(macroNutrient: MacroNutrient): String? {
//        return try {
//            val moshi = Moshi.Builder().build()
//            val jsonAdapter: JsonAdapter<MacroNutrient> = moshi.adapter<MacroNutrient>()
//            return jsonAdapter.toJson(macroNutrient)
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    @OptIn(ExperimentalStdlibApi::class)
//    @TypeConverter
//    fun convertToMacroNutrient(json: String): MacroNutrient? {
//        return try {
//            val moshi = Moshi.Builder().build()
//            val jsonAdapter: JsonAdapter<MacroNutrient> = moshi.adapter<MacroNutrient>()
//            jsonAdapter.fromJson(json)
//        } catch (e: Exception) {
//            null
//        }
//    }
}