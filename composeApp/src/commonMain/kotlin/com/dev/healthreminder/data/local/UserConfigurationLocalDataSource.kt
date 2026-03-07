package com.dev.healthreminder.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.dev.healthreminder.database.HealthReminderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class UserConfigurationLocalDataSource(
    private val db: HealthReminderDatabase,
) {
    private val queries = db.userConfigurationEntityQueries

    suspend fun insertOrReplace(
        reminderId: Long,
        isActive: Boolean,
        interval: Long,
    ) {
        queries.insertOrReplace(
            reminderId = reminderId,
            isActive = if (isActive) 1L else 0L,
            interval = interval,
        )
    }

    fun getAllReminders() = queries.getAllReminders().asFlow().mapToList(Dispatchers.IO)

    suspend fun updateReminderState(
        reminderId: Long,
        isActive: Boolean,
    ) {
        queries.updateReminderState(
            isActive = if (isActive) 1L else 0L,
            reminderId = reminderId,
        )
    }

    suspend fun updateReminderSetting(
        reminderId: Long,
        interval: Long,
    ) {
        queries.updateReminderSetting(
            interval = interval,
            reminderId = reminderId,
        )
    }

    suspend fun clearReminders() {
        queries.clearReminders()
    }
}
