package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.local.entity.ConsumptionEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.FoodEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.util.Result

interface NutritionLogLocalDataSource {
    suspend fun upsertPreference(preferenceEntity: PreferenceEntity): Result

    suspend fun queryPreference(): PreferenceEntity?

    suspend fun upsertFood(foodEntity: FoodEntity): Result

    suspend fun upsertDailyIntakeSummary(consumptionEntity: ConsumptionEntity): Result

    suspend fun queryMostRecentDailyIntakeSummary(): ConsumptionEntity?
}