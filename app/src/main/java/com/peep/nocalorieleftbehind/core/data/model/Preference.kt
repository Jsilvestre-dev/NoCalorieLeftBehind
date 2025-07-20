package com.peep.nocalorieleftbehind.core.data.model

data class Preference(
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int
) {
    companion object {
        fun default() = Preference(
            calories = 0,
            protein = 0,
            carbs = 0,
            fats = 0
        )
    }
}
