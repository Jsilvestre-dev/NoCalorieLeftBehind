package com.peep.nocalorieleftbehind.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peep.nocalorieleftbehind.core.data.local.converter.ListOfLongConverter
import com.peep.nocalorieleftbehind.core.data.local.converter.MapNutrientIntConverter
import com.peep.nocalorieleftbehind.core.data.local.dao.DailySummaryDao
import com.peep.nocalorieleftbehind.core.data.local.dao.FoodDao
import com.peep.nocalorieleftbehind.core.data.local.dao.PreferenceDao
import com.peep.nocalorieleftbehind.core.data.local.entity.ConsumptionEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.FoodEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.PreferenceEntity

@Database(
    entities = [PreferenceEntity::class, FoodEntity::class, ConsumptionEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MapNutrientIntConverter::class, ListOfLongConverter::class)
abstract class NutritionLogDatabase : RoomDatabase() {
    abstract fun preferenceDao(): PreferenceDao

    abstract fun foodDao(): FoodDao

    abstract fun dailyIntakeSummaryDao(): DailySummaryDao
}