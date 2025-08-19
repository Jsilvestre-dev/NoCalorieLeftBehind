package com.peep.nocalorieleftbehind.core.data.mapper

import com.peep.nocalorieleftbehind.core.data.local.entity.ConsumptionEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.FoodEntity
import com.peep.nocalorieleftbehind.core.data.local.entity.PreferenceEntity
import com.peep.nocalorieleftbehind.core.domain.model.Consumption
import com.peep.nocalorieleftbehind.core.domain.model.Food
import com.peep.nocalorieleftbehind.core.domain.model.Preference

fun Preference.toPreferenceEntity() = PreferenceEntity(
    id = 1,
    calories = calories,
    protein = protein,
    carbs = carbs,
    fats = fats,
)

fun Food.toFoodEntity() = FoodEntity(
    id = 0,
    name = name,
    nutrients = nutrients,
    timeStamp = timeStamp
)

fun PreferenceEntity.toPreference(): Preference = Preference(
    calories = calories,
    protein = protein,
    carbs = carbs,
    fats = fats
)

fun Consumption.toDailyIntakeSummaryEntity(): ConsumptionEntity = ConsumptionEntity(
    id = id,
    totalCalories = caloriesEaten,
    totalProtein = proteinEaten,
    totalCarbs = carbsEaten,
    totalFats = fatsEaten,
    foodsEatenIds = foodsEatenIds,
    timeCreated = timeCreated
)

fun ConsumptionEntity.toDailyIntakeSummary(): Consumption = Consumption(
    id = id,
    caloriesEaten = totalCalories,
    proteinEaten = totalProtein,
    carbsEaten = totalCarbs,
    fatsEaten = totalFats,
    foodsEatenIds = foodsEatenIds,
    timeCreated = timeCreated
)