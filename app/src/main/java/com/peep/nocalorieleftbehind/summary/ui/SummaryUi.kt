package com.peep.nocalorieleftbehind.summary.ui

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class SummaryUi(
    val calories: NutrientSummary,
    val nutrientSummaryList: ImmutableList<NutrientSummary>,
    val date: String
)
