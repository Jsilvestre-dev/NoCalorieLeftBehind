package com.peep.nocalorieleftbehind.core.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import com.peep.nocalorieleftbehind.R

@OptIn(ExperimentalTextApi::class)
val jostFamily = FontFamily(
    Font(
        resId = R.font.jost_variablefont_wght, variationSettings = FontVariation.Settings()
    )
)

val fugazOneFamily = FontFamily(
    Font(resId = R.font.fugazone_regular, weight = FontWeight.Normal)
)

@OptIn(ExperimentalTextApi::class)
val remFamily = FontFamily(
    Font(
        resId = R.font.rem_variablefont_wght, variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    )
)