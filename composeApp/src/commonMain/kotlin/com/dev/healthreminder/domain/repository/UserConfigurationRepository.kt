package com.dev.healthreminder.domain.repository

import com.dev.healthreminder.domain.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface UserConfigurationRepository {
    fun getAllReminders(): Flow<List<ReminderModel>>

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
