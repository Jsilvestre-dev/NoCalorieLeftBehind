package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.data.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.util.UiState

@Immutable
data class PreferenceUiState(
    val trackedNutrientLimits: Map<Nutrient, UiState<Int>>
) {
    fun isAllValid(): Boolean {
        return calories.second is Result.Successful && protein.second is Result.Successful && carbs.second is Result.Successful && fats.second is Result.Successful
    }

    fun get(nutrient: Nutrient) = when (nutrient) {
        Nutrient.CALORIES -> this.calories
        Nutrient.PROTEIN -> this.protein
        Nutrient.FATS -> this.fats
        Nutrient.CARBS -> this.carbs
    }

    fun update(valueAndResult: Pair<Int, Result>, nutrient: Nutrient): PreferenceUiState =
        when (nutrient) {
            Nutrient.CALORIES -> copy(calories = valueAndResult)
            Nutrient.PROTEIN -> copy(protein = valueAndResult)
            Nutrient.FATS -> copy(fats = valueAndResult)
            Nutrient.CARBS -> copy(carbs = valueAndResult)
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