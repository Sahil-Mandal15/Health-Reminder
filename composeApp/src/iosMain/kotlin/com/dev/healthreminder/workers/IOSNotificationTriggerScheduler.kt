package com.dev.healthreminder.workers

import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter

internal class IOSNotificationTriggerScheduler : ReminderService {

    override fun start(intervalInMillis: Long) {
        // TODO: Refactor this Notification logic with our custom impl
        val content = UNMutableNotificationContent().apply {
            setTitle("Reminder")
            setBody("Time to do your task")
        }

        val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(
            timeInterval = intervalInMillis / 1_000.0,
            repeats = true
        )

        val request = UNNotificationRequest.requestWithIdentifier(
            identifier = REMINDER_NAME,
            content = content,
            trigger = trigger
        )

        UNUserNotificationCenter.currentNotificationCenter()
            .addNotificationRequest(request) { error ->
                error?.let {
                    // TODO: Events to error channel
                }
            }
    }

    override fun stop() {
        UNUserNotificationCenter.currentNotificationCenter()
            .removePendingNotificationRequestsWithIdentifiers(
                listOf(REMINDER_NAME)
            )
    }
}