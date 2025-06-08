package com.peep.nocalorieleftbehind.welcome

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class MacroNutrient(
    val name: String,
    val amount: Int
)