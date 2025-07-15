package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.mapper.toEntity
import com.peep.nocalorieleftbehind.core.data.mapper.toModel
import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.Result

class IntakeRepositoryImpl(
    val intakeLocalDataSource: IntakeLocalDataSource
) : IntakeRepository {
    override suspend fun savePreference(preference: Preference): Result {
        return intakeLocalDataSource.upsertPreference(preference.toEntity())
    }

    override suspend fun getPreference(): Preference? {
        return intakeLocalDataSource.queryPreference()?.toModel()
    }
}