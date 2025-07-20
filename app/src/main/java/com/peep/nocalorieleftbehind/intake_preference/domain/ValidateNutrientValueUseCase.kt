package com.peep.nocalorieleftbehind.intake_preference.domain

import com.peep.nocalorieleftbehind.core.util.Result

class ValidateNutrientValueUseCase {

    operator fun invoke(value: Int): Pair<Int, Result> {
        return value to getResult(value)
    }

    private fun getResult(value: Int) = if (value >= 0) Result.Successful else Result.Failure

}