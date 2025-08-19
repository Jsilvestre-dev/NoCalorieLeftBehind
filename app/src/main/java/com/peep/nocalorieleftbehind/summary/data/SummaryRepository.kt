package com.peep.nocalorieleftbehind.summary.data

import com.peep.nocalorieleftbehind.core.domain.model.Consumption
import com.peep.nocalorieleftbehind.core.util.Result

interface SummaryRepository {

    suspend fun saveDailyIntakeSummary(consumption: Consumption): Result

    suspend fun getMostRecentDailyIntakeSummary(): Consumption?

}