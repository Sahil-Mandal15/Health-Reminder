package com.dev.healthreminder.workers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class BackgroundReminderWorker(
    private val scope: CoroutineScope
) : ReminderService {

    private var job: Job? = null

    override fun start(intervalInMillis: Long) {
        if (job != null) return

        job = scope.launch {
            while (isActive) {
                //TODO: trigger notification here
                delay(intervalInMillis)
            }
        }
    }

    override fun stop() {
        job?.cancel()
        job = null
    }
}
