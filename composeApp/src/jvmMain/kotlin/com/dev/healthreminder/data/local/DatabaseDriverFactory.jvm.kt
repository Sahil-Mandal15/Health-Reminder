package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME
import java.io.File

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver =
            JdbcSqliteDriver(
                url = "jdbc:sqlite:$LOCAL_DB_NAME",
            )
        val dbFile = File(LOCAL_DB_NAME)
        if (!dbFile.exists()) {
            HealthReminderDatabase.Schema.create(driver).await()
        }
        return driver
    }
}
