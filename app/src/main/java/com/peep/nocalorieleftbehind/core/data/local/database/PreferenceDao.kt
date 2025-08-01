package com.peep.nocalorieleftbehind.core.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.peep.nocalorieleftbehind.core.data.local.database.entity.PreferenceEntity

@Dao
interface PreferenceDao {

    @Upsert
    suspend fun upsertPreference(intakeTargetsEntity: PreferenceEntity): Long

    @Query("SELECT * FROM PreferenceEntity")
    suspend fun getPreference(): PreferenceEntity?

}