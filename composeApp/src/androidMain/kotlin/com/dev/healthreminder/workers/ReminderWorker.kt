package com.dev.healthreminder.workers

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

internal class ReminderWorker(
    private val context: Context
) : ReminderService {

    override fun start(intervalInMillis: Long) {
        val request =
            PeriodicWorkRequestBuilder<ReminderTriggerWorker>(
                intervalInMillis,
                TimeUnit.MILLISECONDS
            ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                REMINDER_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
    }

    override fun stop() {
        WorkManager.getInstance(context)
            .cancelUniqueWork(REMINDER_NAME)
    }
}