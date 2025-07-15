package com.peep.nocalorieleftbehind.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity

@Database(
    entities = [PreferenceEntity::class],
    version = 1,
    exportSchema = true
)
abstract class IntakeDatabase : RoomDatabase() {
    abstract fun preferenceDao(): PreferenceDao
}