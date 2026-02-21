package com.dev.healthreminder.data.local

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME

actual class DatabaseDriverFactory(
    private val context: Context,
) {
    actual suspend fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = HealthReminderDatabase.Schema.synchronous(),
            context = context,
            name = LOCAL_DB_NAME,
        )
}
