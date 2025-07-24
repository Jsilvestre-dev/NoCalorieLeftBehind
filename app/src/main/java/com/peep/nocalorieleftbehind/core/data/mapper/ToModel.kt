package com.peep.nocalorieleftbehind.core.data.mapper

import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.data.model.Preference

fun PreferenceEntity.toModel(): Preference = Preference(
    trackedNutrientLimits = trackedNutrientLimits
)