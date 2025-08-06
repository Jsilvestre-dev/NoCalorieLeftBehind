package com.peep.nocalorieleftbehind.record_intake

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val RecordIntakeModule = module {
    viewModelOf(::RecordIntakeViewModel)
}