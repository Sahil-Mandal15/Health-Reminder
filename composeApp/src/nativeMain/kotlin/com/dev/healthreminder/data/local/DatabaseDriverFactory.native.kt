package com.dev.healthreminder.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            HealthReminderDatabase.Schema.synchronous(),
            "health_reminder.db",
        )
}
