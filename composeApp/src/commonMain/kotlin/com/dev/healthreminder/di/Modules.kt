package com.dev.healthreminder.di

import com.dev.healthreminder.data.local.DatabaseDriverFactory
import com.dev.healthreminder.data.local.UserConfigurationLocalDataSource
import com.dev.healthreminder.data.remote.RemoteDataSource
import com.dev.healthreminder.data.repository.UserConfigurationRepositoryImpl
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.domain.repository.UserConfigurationRepository
import com.dev.healthreminder.presentation.ui.stateHolders.ConfigureViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule =
    module {
        singleOf(::RemoteDataSource)
        singleOf(::UserConfigurationRepositoryImpl) { bind<UserConfigurationRepository>() }
        singleOf(::UserConfigurationLocalDataSource)
        single {
            HealthReminderDatabase(
                driver =
                    runBlocking(Dispatchers.IO) {
                        get<DatabaseDriverFactory>().createDriver()
                    },
            )
        }
        viewModelOf(::ConfigureViewModel)
    }
