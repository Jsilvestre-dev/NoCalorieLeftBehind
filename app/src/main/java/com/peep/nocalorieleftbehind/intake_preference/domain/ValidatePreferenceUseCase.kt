package com.peep.nocalorieleftbehind.intake_preference.domain

import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.data.model.MacroNutrient
import com.peep.nocalorieleftbehind.core.data.model.Preference

class ValidatePreferenceUseCase {

    operator fun invoke(preference: Preference) = mapOf(
        MacroNutrient.CALORIES to validate(preference.calories),
        MacroNutrient.PROTEIN to validate(preference.protein),
        MacroNutrient.CARBS to validate(preference.carbs),
        MacroNutrient.FATS to validate(preference.fats)
    )


    private fun validate(value: Int): Result =
        if (value > 0) Result.Successful else Result.Failure

    private fun validate(value: Int?): Result =
        if (value == null || value > 0) Result.Successful else Result.Failure
}