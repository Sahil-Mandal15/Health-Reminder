package com.dev.healthreminder.presentation.permission

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.dev.healthreminder.presentation.providers.PlatformActivityProvider
import kotlin.random.Random

actual class NotificationPermissionManager(
    private val context: Context,
) {
    actual suspend fun requestPermission(): Boolean {
        if (isPermissionGranted()) return true
        if (Build.VERSION.SDK_INT >= 33) {
            val activity: Activity = PlatformActivityProvider.getCurrentActivity ?: return false
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                909,
            )
        }
        return false
    }

    actual fun isPermissionGranted(): Boolean =
        if (Build.VERSION.SDK_INT >= 33) {
            val checkSelfPermission =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                )
            checkSelfPermission == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    @SuppressLint("MissingPermission")
    actual fun showNotification(
        title: String,
        body: String,
    ) {
        val channelId = "reminder_channel_id"
        val notificationId = Random.nextInt()

        // Android 8+ has channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "General Notification"
            val descriptionTxt = "General notification channel"
            val channel =
                NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = descriptionTxt
                }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val builder =
                NotificationCompat
                    .Builder(context, channelId)
                    .setSmallIcon(1)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                if (isPermissionGranted()) {
                    runCatching {
                        if (isPermissionGranted().not()) {
                            notify(notificationId, builder.build())
                        }
                    }
                }
            }
        }
    }
}
