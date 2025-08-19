package com.peep.nocalorieleftbehind.log_food.di

import com.peep.nocalorieleftbehind.log_food.data.LogFoodRepository
import com.peep.nocalorieleftbehind.log_food.data.LogFoodRepositoryImpl
import com.peep.nocalorieleftbehind.log_food.ui.LogFoodViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val LogFoodModule = module {

    factoryOf(::LogFoodRepositoryImpl){
        bind<LogFoodRepository>()
    }
    viewModelOf(::LogFoodViewModel)
}