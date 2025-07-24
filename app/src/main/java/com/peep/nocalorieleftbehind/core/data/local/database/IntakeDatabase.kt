package com.peep.nocalorieleftbehind.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peep.nocalorieleftbehind.core.data.local.converter.MapNutrientIntConverter
import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity

@Database(
    entities = [PreferenceEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MapNutrientIntConverter::class)
abstract class IntakeDatabase : RoomDatabase() {
    abstract fun preferenceDao(): PreferenceDao
}