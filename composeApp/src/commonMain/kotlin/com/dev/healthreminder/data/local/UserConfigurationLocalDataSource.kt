package com.dev.healthreminder.data.local

import com.dev.healthreminder.data.pojo.UserConfigurationPojo

interface UserConfigurationLocalDataSource {
    fun getAllReminders(): List<UserConfigurationPojo>

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
