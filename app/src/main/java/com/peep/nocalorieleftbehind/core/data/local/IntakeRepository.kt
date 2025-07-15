package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.model.Preference
import com.peep.nocalorieleftbehind.core.util.Result

interface IntakeRepository {
    suspend fun savePreference(preference: Preference): Result

    suspend fun getPreference(): Preference?
}