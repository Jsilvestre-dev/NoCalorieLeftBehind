package com.peep.nocalorieleftbehind.data.local

import com.peep.nocalorieleftbehind.data.mapper.toEntity
import com.peep.nocalorieleftbehind.welcome.IntakeTarget

class IntakeRepositoryImpl(
    val foodIntakeLocalDataSource: FoodIntakeLocalDataSource
) : IntakeRepository {
    override suspend fun saveIntakeTarget(intakeTarget: IntakeTarget) {
        foodIntakeLocalDataSource.upsertIntakeTarget(intakeTarget.toEntity())
    }


}