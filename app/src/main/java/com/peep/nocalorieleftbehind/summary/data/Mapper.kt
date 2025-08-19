package com.peep.nocalorieleftbehind.summary.data

import com.peep.nocalorieleftbehind.core.domain.model.Consumption
import com.peep.nocalorieleftbehind.core.domain.model.Nutrient
import com.peep.nocalorieleftbehind.core.domain.model.Preference
import com.peep.nocalorieleftbehind.summary.ui.NutrientSummary
import com.peep.nocalorieleftbehind.summary.ui.SummaryUi
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class, ExperimentalTime::class, FormatStringsInDatetimeFormats::class)
fun toSummaryUi(consumption: Consumption?, preference: Preference): SummaryUi {

    val caloriesEaten = consumption?.caloriesEaten ?: 0
    val caloriesNutrientSummary = NutrientSummary(
        nutrient = Nutrient.CALORIES,
        eaten = caloriesEaten,
        left = caloriesEaten - preference.calories,
        total = preference.calories
    )

    val nutrientSummaryMap = buildList {
        consumption?.let {
            add(
                NutrientSummary(
                    nutrient = Nutrient.PROTEIN,
                    eaten = it.proteinEaten,
                    left = it.proteinEaten - preference.protein,
                    total = preference.protein
                )
            )
            add(
                NutrientSummary(
                    nutrient = Nutrient.CARBS,
                    eaten = it.carbsEaten,
                    left = it.carbsEaten - preference.carbs,
                    total = preference.carbs
                )
            )
            add(
                NutrientSummary(
                    nutrient = Nutrient.FATS,
                    eaten = it.fatsEaten,
                    left = it.fatsEaten - preference.fats,
                    total = preference.fats
                )
            )
        }
    }.toImmutableList()

    val dateFormat = LocalDate.Format {
        byUnicodePattern("MMM dd, yyyy")
    }

    val formattedDate = consumption?.timeCreated?.let { time ->
        val localDate = LocalDate.fromEpochDays(time)
        localDate.format(dateFormat)
    } ?: Clock.System.todayIn(TimeZone.currentSystemDefault()).format(dateFormat)

    return SummaryUi(
        calories = caloriesNutrientSummary,
        nutrientSummaryList = nutrientSummaryMap,
        date = formattedDate
    )
}