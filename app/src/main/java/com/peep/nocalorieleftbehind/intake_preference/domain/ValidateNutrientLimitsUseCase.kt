package com.peep.nocalorieleftbehind.intake_preference.domain

import com.peep.nocalorieleftbehind.core.data.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.util.UiState

class ValidateNutrientLimitsUseCase {

    operator fun invoke(nutrientAmount: Int): UiState<Int> {
        return getResult(nutrientAmount)
    }

    operator fun invoke(trackedNutrientLimits: Map<Nutrient, Int>): Map<Nutrient, UiState<Int>> {
        return trackedNutrientLimits.mapValues { nutrient ->
            getResult(nutrient.value)
        }
    }

    private fun getResult(amount: Int) = if (amount >= 0) UiState.Success(amount) else UiState.Error()

}