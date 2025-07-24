package com.peep.nocalorieleftbehind.add_food

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val AddFoodModule = module {
    viewModelOf(::AddFoodViewModel)
}