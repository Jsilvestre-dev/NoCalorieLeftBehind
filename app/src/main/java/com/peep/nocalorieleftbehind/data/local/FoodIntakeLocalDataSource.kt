package com.peep.nocalorieleftbehind.data.local

import com.peep.nocalorieleftbehind.data.local.entity.IntakeTargetEntity

interface FoodIntakeLocalDataSource {
    suspend fun upsertIntakeTarget(intakeTargetEntity: IntakeTargetEntity)
}