package com.peep.nocalorieleftbehind.intake_preference.domain

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.delete
import androidx.compose.runtime.Stable

/**
 * Removes leading zero char from input
 */
@Stable
class NutrientValueInputTransformation : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        if (length > 1 && originalText.contains(Regex("^0"))) delete(0, 1)
    }
}