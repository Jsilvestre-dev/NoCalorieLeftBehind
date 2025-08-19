package com.peep.nocalorieleftbehind.preference.data

import com.peep.nocalorieleftbehind.core.domain.model.Preference
import com.peep.nocalorieleftbehind.core.util.Result

interface PreferenceRepository {

    suspend fun savePreference(preference: Preference): Result

    suspend fun getPreference(): Preference?

}