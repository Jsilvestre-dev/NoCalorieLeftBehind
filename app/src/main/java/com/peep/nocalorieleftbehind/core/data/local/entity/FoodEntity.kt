package com.peep.nocalorieleftbehind.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient

@Entity
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val nutrients: Map<Nutrient, Int>,
    val timeStamp: Long
)