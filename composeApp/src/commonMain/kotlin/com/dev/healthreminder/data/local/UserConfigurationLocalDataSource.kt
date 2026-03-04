package com.dev.healthreminder.data.local

interface UserConfigurationLocalDataSource {
    suspend fun insertOrReplace(
        reminderId: Long,
        isActive: Boolean,
        interval: Long,
    )

    suspend fun updateReminderState(
        reminderId: Long,
        isActive: Boolean,
    )

    suspend fun updateReminderSetting(
        reminderId: Long,
        interval: Long,
    )

    suspend fun clearReminders()
}
