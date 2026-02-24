package com.dev.healthreminder.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderTriggerWorker(
    context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        // TODO: attach Notification Trigger here
        return Result.Success()
    }
}
