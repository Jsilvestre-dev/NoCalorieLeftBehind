package com.peep.nocalorieleftbehind.log_food.data

import com.peep.nocalorieleftbehind.core.data.local.NutritionLogLocalDataSource
import com.peep.nocalorieleftbehind.core.data.mapper.toFoodEntity
import com.peep.nocalorieleftbehind.core.domain.model.Food
import com.peep.nocalorieleftbehind.core.util.Result

class LogFoodRepositoryImpl(
    private val localDataSource: NutritionLogLocalDataSource
): LogFoodRepository{

    override suspend fun saveFood(food: Food): Result {
        return localDataSource.upsertFood(food.toFoodEntity())
    }

}