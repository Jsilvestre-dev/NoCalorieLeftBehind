package com.peep.nocalorieleftbehind.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConsumptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val totalCalories: Int,
    val totalProtein: Int,
    val totalCarbs: Int,
    val totalFats: Int,
    val foodsEatenIds: List<Long>,
    val timeCreated: Long
)