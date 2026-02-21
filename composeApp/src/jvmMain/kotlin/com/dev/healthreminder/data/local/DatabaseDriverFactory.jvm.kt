package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:health_reminder.db",
        ).also {
            HealthReminderDatabase.Schema.create(it).await()
        }
}
