package com.peep.nocalorieleftbehind.data.local

import com.peep.nocalorieleftbehind.data.local.entity.IntakeTargetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FoodIntakeLocalDataSourceImpl(
    private val intakeTargetsDao: IntakeTargetDao
) : FoodIntakeLocalDataSource {
    override suspend fun upsertIntakeTarget(intakeTargetEntity: IntakeTargetEntity) {
        withContext(Dispatchers.IO) {
            intakeTargetsDao.upsert(intakeTargetEntity)
        }
    }

}