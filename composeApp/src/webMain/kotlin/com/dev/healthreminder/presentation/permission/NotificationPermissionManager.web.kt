package com.dev.healthreminder.presentation.permission

actual class NotificationPermissionManager {
    actual suspend fun requestPermission(): Boolean = true

    actual fun isPermissionGranted(): Boolean = true

    actual fun showNotification(
        title: String,
        body: String,
    ) {
        // Handling not required
    }
}
