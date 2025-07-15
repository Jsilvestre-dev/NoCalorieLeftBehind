package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.util.Result

interface IntakeLocalDataSource {
    suspend fun upsertPreference(preferenceEntity: PreferenceEntity): Result

    suspend fun queryPreference(): PreferenceEntity?
}