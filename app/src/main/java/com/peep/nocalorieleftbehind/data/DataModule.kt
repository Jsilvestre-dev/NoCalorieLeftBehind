package com.peep.nocalorieleftbehind.data

import androidx.room.ExperimentalRoomApi
import androidx.room.Room
import com.peep.nocalorieleftbehind.data.local.FoodIntakeDatabase
import com.peep.nocalorieleftbehind.data.local.FoodIntakeLocalDataSource
import com.peep.nocalorieleftbehind.data.local.FoodIntakeLocalDataSourceImpl
import com.peep.nocalorieleftbehind.data.local.IntakeRepository
import com.peep.nocalorieleftbehind.data.local.IntakeRepositoryImpl
import com.peep.nocalorieleftbehind.data.local.converter.MacroNutrientConverter
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalRoomApi::class)
val DataModule = module {

    single {
        Room
            .databaseBuilder(
                context = androidContext(),
                klass = FoodIntakeDatabase::class.java,
                name = "food-intake-db"
            )
            .setAutoCloseTimeout(10L, TimeUnit.SECONDS)
            .build()
    }

    single { get<FoodIntakeDatabase>().dailyIntakeTargetsDao() }

    single<IntakeRepository> {
        IntakeRepositoryImpl(get())
    }

    single<FoodIntakeLocalDataSource> {
        FoodIntakeLocalDataSourceImpl(get())
    }
}