package com.dev.healthreminder.domain.repository

import com.dev.healthreminder.domain.model.ConfigureItemModel
import kotlinx.coroutines.flow.Flow

interface UserConfigurationRepository {
    fun getAllReminders(): Flow<List<ConfigureItemModel>>

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
