package com.peep.nocalorieleftbehind.core.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreferenceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val calories: Int,
    val protein: Int?,
    val carbs: Int?,
    val fats: Int?
)