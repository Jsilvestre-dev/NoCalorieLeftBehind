package com.peep.nocalorieleftbehind.intake_preference.di

import com.peep.nocalorieleftbehind.intake_preference.domain.ValidateNutrientLimitsUseCase
import com.peep.nocalorieleftbehind.intake_preference.ui.PreferenceViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val PreferenceModule = module {

    singleOf(::ValidateNutrientLimitsUseCase)

    viewModelOf(::PreferenceViewModel)
}