package com.peep.nocalorieleftbehind.intake_preference.domain

import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.UiState
import com.peep.nocalorieleftbehind.intake_preference.ui.PreferenceUiState

class ValidateNutrientValueUseCase {

    operator fun invoke(nutrientAmount: Int?): UiState<Int> {
        return getResult(nutrientAmount)
    }

    operator fun invoke(preference: Preference): PreferenceUiState {
        return PreferenceUiState(
            calories = getResult(preference.calories),
            protein = getResult(preference.protein),
            carbs = getResult(preference.carbs),
            fats = getResult(preference.fats)
        )
    }

    private fun getResult(amount: Int?) =
        if (amount != null && amount >= 0) UiState.Success(amount) else UiState.Error()

}