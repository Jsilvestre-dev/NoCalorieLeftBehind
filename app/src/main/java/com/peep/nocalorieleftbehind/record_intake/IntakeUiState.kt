package com.peep.nocalorieleftbehind.record_intake

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.data.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.UiState

@Immutable
data class IntakeUiState(
    val nutrients: List<Nutrient>,
    val foodNutrients: Map<Nutrient, UiState<Int>>
) {
    companion object {
        val default = IntakeUiState(
            nutrients = listOf(Nutrient.CALORIES),
            foodNutrients = mapOf()
        )
    }
}