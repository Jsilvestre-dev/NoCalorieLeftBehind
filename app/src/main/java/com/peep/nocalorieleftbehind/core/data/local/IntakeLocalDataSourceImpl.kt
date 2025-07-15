package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.local.database.PreferenceDao
import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.util.getRoomResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class IntakeLocalDataSourceImpl(
    private val preferenceDao: PreferenceDao
) : IntakeLocalDataSource {
    override suspend fun upsertPreference(preferenceEntity: PreferenceEntity): Result =
        withContext(Dispatchers.IO) {
            preferenceDao.upsertPreference(preferenceEntity)
        }.getRoomResult()

    override suspend fun queryPreference(): PreferenceEntity? =
        withContext(Dispatchers.IO) {
            preferenceDao.getPreference()
        }
}