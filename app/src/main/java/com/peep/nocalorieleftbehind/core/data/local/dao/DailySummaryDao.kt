package com.peep.nocalorieleftbehind.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.peep.nocalorieleftbehind.core.data.local.entity.ConsumptionEntity

@Dao
interface DailySummaryDao {

    @Upsert
    suspend fun upsertDailyIntakeSummary(consumptionEntity: ConsumptionEntity): Long

    @Query("SELECT * FROM ConsumptionEntity ORDER BY id DESC LIMIT 1")
    suspend fun getMostRecentDailyIntakeSummary(): ConsumptionEntity?

}