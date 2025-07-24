package com.peep.nocalorieleftbehind.core.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.peep.nocalorieleftbehind.core.data.local.converter.MapNutrientIntConverter
import com.peep.nocalorieleftbehind.core.data.model.Nutrient

@Entity
data class PreferenceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(MapNutrientIntConverter::class)
    val trackedNutrientLimits: Map<Nutrient, Int>
)