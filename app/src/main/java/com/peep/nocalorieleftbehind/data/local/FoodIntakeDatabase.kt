package com.peep.nocalorieleftbehind.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peep.nocalorieleftbehind.data.local.IntakeTargetDao
import com.peep.nocalorieleftbehind.data.local.converter.MacroNutrientConverter
import com.peep.nocalorieleftbehind.data.local.entity.IntakeTargetEntity

@Database(
    entities = [IntakeTargetEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MacroNutrientConverter::class)
abstract class FoodIntakeDatabase : RoomDatabase() {
    abstract fun dailyIntakeTargetsDao(): IntakeTargetDao
}