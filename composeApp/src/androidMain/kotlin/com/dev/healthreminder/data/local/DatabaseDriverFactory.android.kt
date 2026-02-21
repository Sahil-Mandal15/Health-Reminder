package com.dev.healthreminder.data.local

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase

actual class DatabaseDriverFactory(
    private val context: Context,
) {
    actual suspend fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = HealthReminderDatabase.Schema.synchronous(),
            context = context,
            name = "health_reminder.db",
        )
}
