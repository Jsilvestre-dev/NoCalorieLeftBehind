package com.peep.nocalorieleftbehind.core.domain.model

data class Consumption(
    val id: Long,
    val caloriesEaten: Int,
    val proteinEaten: Int,
    val carbsEaten: Int,
    val fatsEaten: Int,
    val foodsEatenIds: List<Long>,
    val timeCreated: Long
)