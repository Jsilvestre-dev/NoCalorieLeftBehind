package com.peep.nocalorieleftbehind.core.di

import androidx.room.ExperimentalRoomApi
import androidx.room.Room
import com.peep.nocalorieleftbehind.core.data.local.IntakeLocalDataSource
import com.peep.nocalorieleftbehind.core.data.local.IntakeLocalDataSourceImpl
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.core.data.local.IntakeRepositoryImpl
import com.peep.nocalorieleftbehind.core.data.local.database.IntakeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalRoomApi::class)
val DataModule = module {

    single {
        Room
            .databaseBuilder(
                context = androidContext().applicationContext,
                klass = IntakeDatabase::class.java,
                name = "food-intake-db"
            )
            .setAutoCloseTimeout(10L, TimeUnit.SECONDS)
            .build()
    }

    single { get<IntakeDatabase>().preferenceDao() }

    single<IntakeRepository> {
        IntakeRepositoryImpl(get())
    }

    single<IntakeLocalDataSource> {
        IntakeLocalDataSourceImpl(get())
    }
}