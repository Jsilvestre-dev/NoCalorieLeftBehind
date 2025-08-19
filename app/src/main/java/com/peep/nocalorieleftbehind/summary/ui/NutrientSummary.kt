package com.peep.nocalorieleftbehind.summary.ui

import com.peep.nocalorieleftbehind.core.domain.model.Nutrient

data class NutrientSummary(
    val nutrient: Nutrient,
    val eaten: Int,
    val left: Int,
    val total: Int
)
