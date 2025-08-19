package com.peep.nocalorieleftbehind.log_food.data

import com.peep.nocalorieleftbehind.core.domain.model.Food
import com.peep.nocalorieleftbehind.core.util.Result

interface LogFoodRepository {

    suspend fun saveFood(food: Food): Result

}