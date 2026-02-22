package com.dev.healthreminder.presentation.permission

expect class NotificationPermissionManager {
    suspend fun requestPermission(): Boolean

    fun isPermissionGranted(): Boolean

    fun showNotification(
        title: String,
        body: String,
    )
}
