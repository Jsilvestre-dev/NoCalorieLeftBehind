package com.peep.nocalorieleftbehind.core.data.local

import com.peep.nocalorieleftbehind.core.data.local.dao.DailySummaryDao
import com.peep.nocalorieleftbehind.core.data.local.dao.FoodDao
import com.peep.nocalorieleftbehind.core.data.local.dao.PreferenceDao
import com.peep.nocalorieleftbehind.core.data.local.entity.ConsumptionEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.FoodEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.util.Result
import com.peep.nocalorieleftbehind.core.util.getRoomResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NutritionLogLocalDataSourceImpl(
    private val preferenceDao: PreferenceDao,
    private val foodDao: FoodDao,
    private val dailySummaryDao: DailySummaryDao
) : NutritionLogLocalDataSource {
    override suspend fun upsertPreference(preferenceEntity: PreferenceEntity): Result =
        withContext(Dispatchers.IO) {
            preferenceDao.upsertPreference(preferenceEntity)
        }.getRoomResult()

    override suspend fun queryPreference(): PreferenceEntity? =
        withContext(Dispatchers.IO) {
            preferenceDao.getPreference()
        }

    override suspend fun upsertFood(foodEntity: FoodEntity): Result =
        withContext(Dispatchers.IO) {
            foodDao.upsertFood(foodEntity)
        }.getRoomResult()

    override suspend fun upsertDailyIntakeSummary(consumptionEntity: ConsumptionEntity): Result =
        withContext(Dispatchers.IO) {
            dailySummaryDao.upsertDailyIntakeSummary(consumptionEntity)
        }.getRoomResult()

    override suspend fun queryMostRecentDailyIntakeSummary(): ConsumptionEntity? =
        withContext(Dispatchers.IO) {
            dailySummaryDao.getMostRecentDailyIntakeSummary()
        }
}