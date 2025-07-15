package com.peep.nocalorieleftbehind.core.data.model

data class Preference(
    val calories: Int,
    val protein: Int?,
    val carbs: Int?,
    val fats: Int?
) {
    companion object {
        fun default() = Preference(
            calories = 0,
            protein = null,
            carbs = null,
            fats = null
        )
    }
}
