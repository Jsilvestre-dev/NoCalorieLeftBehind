package com.peep.nocalorieleftbehind.welcome

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val WelcomeModule = module {
    viewModelOf(::WelcomeViewModel)
}