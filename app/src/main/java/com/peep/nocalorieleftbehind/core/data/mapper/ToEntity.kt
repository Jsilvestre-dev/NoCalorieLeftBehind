package com.peep.nocalorieleftbehind.core.data.mapper

import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.data.model.Preference

fun Preference.toEntity() = PreferenceEntity(
    id = 1,
    calories = calories,
    protein = protein,
    carbs = carbs,
    fats = fats,
)