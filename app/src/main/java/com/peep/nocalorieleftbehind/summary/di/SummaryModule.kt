package com.peep.nocalorieleftbehind.summary.di

import com.peep.nocalorieleftbehind.summary.ui.SummaryViewModel
import com.peep.nocalorieleftbehind.summary.data.SummaryRepository
import com.peep.nocalorieleftbehind.summary.data.SummaryRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val SummaryModule = module {

    factoryOf(::SummaryRepositoryImpl) {
        bind<SummaryRepository>()
    }

    viewModelOf(::SummaryViewModel)
}