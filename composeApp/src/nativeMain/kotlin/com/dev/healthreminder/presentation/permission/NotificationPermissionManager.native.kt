package com.dev.healthreminder.presentation.permission

import platform.Foundation.timeIntervalSince1970
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class NotificationPermissionManager {
    init {
        val centerIns = UNUserNotificationCenter.currentNotificationCenter()
        val options =
            UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge

        centerIns.requestAuthorizationWithOptions(options) { granted, error ->
            // No-OP: used to add analytics, currently we dont support
        }
    }

    actual suspend fun requestPermission(): Boolean =
        suspendCoroutine { cont ->
            val center =
                UNUserNotificationCenter.currentNotificationCenter()
            val options =
                UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
            center.requestAuthorizationWithOptions(options) { isGranted, err ->
                if (err != null) {
                    cont.resume(false)
                } else {
                    cont.resume(isGranted)
                }
            }
        }

    actual fun isPermissionGranted(): Boolean = true

    actual fun showNotification(
        title: String,
        body: String,
    ) {
        val notifConfig =
            UNMutableNotificationContent().apply {
                setTitle(title)
                setBody(body)
                setSound(platform.UserNotifications.UNNotificationSound.defaultSound)
            }
        val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(1.0, false)

        val request =
            UNNotificationRequest.requestWithIdentifier(
                identifier = "notification_${platform.Foundation.NSDate().timeIntervalSince1970}",
                content = notifConfig,
                trigger = trigger,
            )

        UNUserNotificationCenter
            .currentNotificationCenter()
            .addNotificationRequest(request) { err ->
                // No-OP: used to add analytics, currently we dont support
            }
    }
}
