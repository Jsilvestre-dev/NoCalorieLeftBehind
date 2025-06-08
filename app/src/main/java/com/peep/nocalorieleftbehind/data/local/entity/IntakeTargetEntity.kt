package com.peep.nocalorieleftbehind.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.peep.nocalorieleftbehind.data.local.converter.MacroNutrientConverter
import com.peep.nocalorieleftbehind.welcome.MacroNutrient

@Entity
data class IntakeTargetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(MacroNutrientConverter::class)
    val calories: MacroNutrient,
    @TypeConverters(MacroNutrientConverter::class)
    val protein: MacroNutrient?,
    @TypeConverters(MacroNutrientConverter::class)
    val fats: MacroNutrient?,
    @TypeConverters(MacroNutrientConverter::class)
    val carbs: MacroNutrient?
)