package com.dev.healthreminder.data.repository

import com.dev.healthreminder.data.local.UserConfigurationLocalDataSource
import com.dev.healthreminder.data.mapper.toModel
import com.dev.healthreminder.domain.model.ReminderModel
import com.dev.healthreminder.domain.repository.UserConfigurationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserConfigurationRepositoryImpl(
    private val dataSource: UserConfigurationLocalDataSource,
) : UserConfigurationRepository {
    override fun getAllReminders(): Flow<List<ReminderModel>> =
        flow {
            emit(dataSource.getAllReminders().map { it.toModel() })
        }.flowOn(Dispatchers.Default)

    override suspend fun updateReminderState(
        reminderId: Long,
        isActive: Boolean,
    ) {
        dataSource.updateReminderState(
            reminderId,
            isActive,
        )
    }

    override suspend fun updateReminderSetting(
        reminderId: Long,
        interval: Long,
    ) {
        dataSource.updateReminderSetting(
            reminderId,
            interval,
        )
    }

    override suspend fun clearReminders() {
        dataSource.clearReminders()
    }
}
