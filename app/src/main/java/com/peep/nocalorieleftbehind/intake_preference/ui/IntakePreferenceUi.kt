package com.peep.nocalorieleftbehind.intake_preference.ui

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.core.data.model.MacroNutrient
import com.peep.nocalorieleftbehind.core.util.Result

@Immutable
data class IntakePreferenceUi(
    val calories: Pair<Int, Result?>,
    val protein: Pair<Int?, Result?>,
    val carbs: Pair<Int?, Result?>,
    val fats: Pair<Int?, Result?>
) {
    fun isAllValid(): Boolean {
        return calories is Result.Successful && protein is Result.Successful && carbs is Result.Successful && fats is Result.Successful
    }

    fun get(nutrient: MacroNutrient) = when (nutrient) {
        MacroNutrient.CALORIES -> this.calories
        MacroNutrient.PROTEIN -> this.protein
        MacroNutrient.FATS -> this.fats
        MacroNutrient.CARBS -> this.carbs
    }
}