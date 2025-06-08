package com.peep.nocalorieleftbehind.data.local

import androidx.room.Dao
import androidx.room.Upsert
import com.peep.nocalorieleftbehind.data.local.entity.IntakeTargetEntity

@Dao
interface IntakeTargetDao {

    @Upsert
    fun upsert(intakeTargetsEntity: IntakeTargetEntity)

}