package com.dev.healthreminder.data.repository

import com.dev.healthreminder.data.local.UserConfigurationLocalDataSource
import com.dev.healthreminder.data.mapper.toConfigureItemModel
import com.dev.healthreminder.data.remote.RemoteDataSource
import com.dev.healthreminder.domain.model.ConfigureItemModel
import com.dev.healthreminder.domain.repository.UserConfigurationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserConfigurationRepositoryImpl(
    private val db: UserConfigurationLocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : UserConfigurationRepository {
    override fun getAllReminders(): Flow<List<ConfigureItemModel>> =
        flow {
            emit(remoteDataSource.getUserConfigurationResponse.map { it.toConfigureItemModel() })
        }.flowOn(Dispatchers.Default)

    override suspend fun updateReminderState(
        reminderId: Long,
        isActive: Boolean,
    ) {
        db.updateReminderState(
            reminderId,
            isActive,
        )
    }

    override suspend fun updateReminderSetting(
        reminderId: Long,
        interval: Long,
    ) {
        db.updateReminderSetting(
            reminderId,
            interval,
        )
    }

    override suspend fun clearReminders() {
        db.clearReminders()
    }
}
