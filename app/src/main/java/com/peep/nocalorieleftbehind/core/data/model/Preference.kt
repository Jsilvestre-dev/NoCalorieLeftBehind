package com.peep.nocalorieleftbehind.core.data.model

data class Preference(
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int
) {
    fun getNutrientList(): List<Nutrient> = buildList {
        add(Nutrient.CALORIES)
        if (protein > 0) add(Nutrient.PROTEIN)
        if (carbs > 0) add(Nutrient.CARBS)
        if (fats > 0) add(Nutrient.FATS)
    }
}
