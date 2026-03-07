package com.dev.healthreminder.di

import com.dev.healthreminder.data.local.DatabaseDriverFactory
import com.dev.healthreminder.presentation.permission.NotificationPermissionManager
import com.dev.healthreminder.workers.IOSNotificationTriggerScheduler
import com.dev.healthreminder.workers.ReminderService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule =
    module {
        singleOf(::NotificationPermissionManager)
        singleOf(::DatabaseDriverFactory)
        singleOf(::IOSNotificationTriggerScheduler).bind(ReminderService::class)
    }
