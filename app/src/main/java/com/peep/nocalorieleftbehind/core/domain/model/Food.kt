package com.peep.nocalorieleftbehind.core.domain.model

data class Food(
    val name: String,
    val nutrients: Map<Nutrient, Int>,
    val timeStamp: Long
)