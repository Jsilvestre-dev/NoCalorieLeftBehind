package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.data.model.MacroNutrient
import com.peep.nocalorieleftbehind.core.util.Result

@Immutable
data class PreferenceUiState(
    val calories: Pair<Int, Result>,
    val protein: Pair<Int, Result>,
    val carbs: Pair<Int, Result>,
    val fats: Pair<Int, Result>
) {
    fun isAllValid(): Boolean {
        return calories.second is Result.Successful && protein.second is Result.Successful && carbs.second is Result.Successful && fats.second is Result.Successful
    }

    fun get(nutrient: MacroNutrient) = when (nutrient) {
        MacroNutrient.CALORIES -> this.calories
        MacroNutrient.PROTEIN -> this.protein
        MacroNutrient.FATS -> this.fats
        MacroNutrient.CARBS -> this.carbs
    }

    fun update(valueAndResult: Pair<Int, Result>, nutrient: MacroNutrient): PreferenceUiState = when (nutrient) {
        MacroNutrient.CALORIES -> copy(calories = valueAndResult)
        MacroNutrient.PROTEIN -> copy(protein = valueAndResult)
        MacroNutrient.FATS -> copy(fats = valueAndResult)
        MacroNutrient.CARBS -> copy(carbs = valueAndResult)
    }

    companion object {
        fun default() = PreferenceUiState(
            calories = 0 to Result.Failure,
            protein = 0 to Result.Successful,
            carbs = 0 to Result.Successful,
            fats = 0 to Result.Successful
        )
    }
}