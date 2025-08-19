package com.peep.nocalorieleftbehind.preference.data

import com.peep.nocalorieleftbehind.core.data.local.NutritionLogLocalDataSource
import com.peep.nocalorieleftbehind.core.data.mapper.toPreference
import com.peep.nocalorieleftbehind.core.data.mapper.toPreferenceEntity
import com.peep.nocalorieleftbehind.core.domain.model.Preference
import com.peep.nocalorieleftbehind.core.util.Result


class PreferenceRepositoryImpl(
    private val nutritionLogLocalDataSource: NutritionLogLocalDataSource
) : PreferenceRepository {

    override suspend fun savePreference(preference: Preference): Result {
        return nutritionLogLocalDataSource.upsertPreference(preference.toPreferenceEntity())
    }

    override suspend fun getPreference(): Preference? {
        return nutritionLogLocalDataSource.queryPreference()?.toPreference()
    }
}