package com.peep.nocalorieleftbehind.core.domain.model

import com.peep.nocalorieleftbehind.R

enum class Nutrient(val resId: Int, val iconResId: Int?) {
    CALORIES(resId = R.string.calories, iconResId = null),
    PROTEIN(resId = R.string.protein, iconResId = R.drawable.outline_meat_24),
    FATS(resId = R.string.fats, iconResId = R.drawable.outline_oil_24),
    CARBS(resId = R.string.carbs, iconResId = R.drawable.outline_bread_24)
}