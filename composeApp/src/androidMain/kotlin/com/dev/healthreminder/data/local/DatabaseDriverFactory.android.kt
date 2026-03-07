package com.dev.healthreminder.data.local

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME

// TODO: Ensuring the proper handling of the constructor for the actual declaration
actual class DatabaseDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = HealthReminderDatabase.Schema.synchronous(),
            context = context,
            name = LOCAL_DB_NAME,
        )
}
