package com.peep.nocalorieleftbehind.summary.data

import com.peep.nocalorieleftbehind.core.data.local.NutritionLogLocalDataSource
import com.peep.nocalorieleftbehind.core.data.mapper.toDailyIntakeSummary
import com.peep.nocalorieleftbehind.core.data.mapper.toDailyIntakeSummaryEntity
import com.peep.nocalorieleftbehind.core.domain.model.Consumption
import com.peep.nocalorieleftbehind.core.util.Result

class SummaryRepositoryImpl(
    private val nutritionLogLocalDataSource: NutritionLogLocalDataSource
) : SummaryRepository {

    override suspend fun saveDailyIntakeSummary(consumption: Consumption): Result {
        return nutritionLogLocalDataSource.upsertDailyIntakeSummary(consumption.toDailyIntakeSummaryEntity())
    }

    override suspend fun getMostRecentDailyIntakeSummary(): Consumption? {
        return nutritionLogLocalDataSource.queryMostRecentDailyIntakeSummary()?.toDailyIntakeSummary()
    }

}