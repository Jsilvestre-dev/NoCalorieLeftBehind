package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.data.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.UiState

@Immutable
data class PreferenceUiState(
    val trackedNutrientLimits: Map<Nutrient, UiState<Int>>
) {
    fun isAllValid(): Boolean {
        return !this.trackedNutrientLimits.values.let { it.contains(UiState.Loading) || it.contains(UiState.Error()) }
    }

    companion object {
        fun default() = PreferenceUiState(
            trackedNutrientLimits = mapOf()
        )
    }
}