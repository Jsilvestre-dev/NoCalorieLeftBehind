package com.peep.nocalorieleftbehind.data.mapper

import com.peep.nocalorieleftbehind.data.local.entity.IntakeTargetEntity
import com.peep.nocalorieleftbehind.welcome.IntakeTarget

fun IntakeTarget.toEntity() = IntakeTargetEntity(
    id = 1,
    calories = calories,
    protein = protein,
    fats = fats,
    carbs = carbs
)