package com.dev.healthreminder.di

import androidx.work.WorkerParameters
import com.dev.healthreminder.presentation.permission.NotificationPermissionManager
import com.dev.healthreminder.workers.ReminderTriggerWorker
import com.dev.healthreminder.workers.ReminderWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule =
    module {
        singleOf(::NotificationPermissionManager)
        singleOf(::ReminderWorker)
        factory { (workerParams: WorkerParameters) ->
            ReminderTriggerWorker(androidContext(), workerParams)
        }
    }
