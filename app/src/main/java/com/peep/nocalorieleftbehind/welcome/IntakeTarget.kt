package com.peep.nocalorieleftbehind.welcome

import androidx.compose.runtime.Immutable
import com.peep.nocalorieleftbehind.welcome.MacroNutrient

@Immutable
data class IntakeTarget(
    val calories: MacroNutrient,
    val protein: MacroNutrient?,
    val fats: MacroNutrient?,
    val carbs: MacroNutrient?
)