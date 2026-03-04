package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:$LOCAL_DB_NAME",
        ).also {
            HealthReminderDatabase.Schema.create(it).await()
        }
}
