package com.peep.nocalorieleftbehind.dashboard

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val DashboardModule = module {
    viewModelOf(::DashboardViewModel)
}