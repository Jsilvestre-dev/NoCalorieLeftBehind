package com.peep.nocalorieleftbehind.core.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.peep.nocalorieleftbehind.core.data.local.entity.FoodEntity

@Dao
interface FoodDao {

    @Upsert
    suspend fun upsertFood(foodEntity: FoodEntity): Long

//    @Query("SELECT * FROM PreferenceEntity")
//    suspend fun getPreference(): PreferenceEntity?

}