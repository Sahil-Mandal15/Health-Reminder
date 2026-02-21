package com.dev.healthreminder.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            HealthReminderDatabase.Schema.synchronous(),
            LOCAL_DB_NAME,
        )
}
