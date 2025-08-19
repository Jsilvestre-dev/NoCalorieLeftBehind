package com.peep.nocalorieleftbehind.preference.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.core.util.UiState.Success

@Immutable
data class PreferenceUiState(
    val calories: UiState<Int>,
    val protein: UiState<Int>,
    val carbs: UiState<Int>,
    val fats: UiState<Int>
) {
    fun isAllValid(): Boolean {
        return calories is Success && protein is Success && carbs is Success && fats is Success
    }

    fun updateNutrient(nutrient: Nutrient, uiState: UiState<Int>) = when (nutrient) {
        Nutrient.CALORIES -> copy(calories = uiState)
        Nutrient.PROTEIN -> copy(protein = uiState)
        Nutrient.FATS -> copy(fats = uiState)
        Nutrient.CARBS -> copy(carbs = uiState)
    }

    fun getNutrient(nutrient: Nutrient): UiState<Int> = when (nutrient) {
        Nutrient.CALORIES -> calories
        Nutrient.PROTEIN -> protein
        Nutrient.FATS -> fats
        Nutrient.CARBS -> carbs
    }

    companion object {
        fun default() = PreferenceUiState(
            calories = Success(0),
            protein = Success(0),
            carbs = Success(0),
            fats = Success(0)
        )
    }
}