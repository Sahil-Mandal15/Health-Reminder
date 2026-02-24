package com.dev.healthreminder.workers

interface ReminderService {
    fun start(intervalInMillis: Long)
    fun stop()
}