package com.peep.nocalorieleftbehind.core.domain.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class Preference(
    val calories: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0
) {
    fun getNutrientList(): ImmutableList<Nutrient> = buildList {
        add(Nutrient.CALORIES)
        if (protein > 0) add(Nutrient.PROTEIN)
        if (carbs > 0) add(Nutrient.CARBS)
        if (fats > 0) add(Nutrient.FATS)
    }.toImmutableList()
}
