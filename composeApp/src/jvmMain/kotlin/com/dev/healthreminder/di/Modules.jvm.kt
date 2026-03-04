package com.dev.healthreminder.di

import com.dev.healthreminder.presentation.permission.NotificationPermissionManager
import com.dev.healthreminder.workers.BackgroundReminderWorker
import com.dev.healthreminder.workers.ReminderService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule =
    module {
        single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
        singleOf(::NotificationPermissionManager)
        singleOf(::BackgroundReminderWorker).bind(ReminderService::class)
    }
