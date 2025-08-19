package com.peep.nocalorieleftbehind.log_food.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.util.UiState

@Immutable
data class FoodUiState(
    val foodName: UiState<String>,
    val foodNutrients: Map<Nutrient, UiState<Int>>
) {

    fun isFoodUiInvalid() = foodNutrients.containsValue(UiState.Error()) || foodName is UiState.Error

    fun toNutrientIntMap(): Map<Nutrient, Int> = foodNutrients.mapValues { (_, value) ->
        (value as UiState.Success).data
    }

    companion object {
        val default = FoodUiState(
            foodName = UiState.Success(""),
            foodNutrients = mapOf()
        )
    }
}