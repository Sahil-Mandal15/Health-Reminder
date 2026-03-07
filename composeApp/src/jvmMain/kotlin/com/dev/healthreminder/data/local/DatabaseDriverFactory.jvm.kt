package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.utils.LOCAL_DB_NAME
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val parentDirectory = File(System.getProperty("user.home"), ".health_reminder")
        if (parentDirectory.exists().not()) {
            parentDirectory.mkdirs()
        }
        val dbFile = File(parentDirectory, LOCAL_DB_NAME)

        val driver =
            JdbcSqliteDriver(
                "jdbc:sqlite:${dbFile.absolutePath}",
            )

        initDriver(driver)

        return driver
    }

    private fun initDriver(driver: JdbcSqliteDriver) {
        val currentSchemaVersion = HealthReminderDatabase.Schema.version
        val userVersion = getUserSchemaVersion(driver)

        if (userVersion == 0L) {
            HealthReminderDatabase.Schema.create(driver)
            setUserSchemaVersion(driver, currentSchemaVersion)
        } else if (userVersion < currentSchemaVersion) {
            HealthReminderDatabase.Schema.migrate(
                driver,
                userVersion,
                currentSchemaVersion,
            )
            setUserSchemaVersion(driver, currentSchemaVersion)
        }
    }

    private fun getUserSchemaVersion(driver: JdbcSqliteDriver) =
        driver
            .executeQuery(
                identifier = null,
                sql = "PRAGMA user_version;",
                parameters = 0,
                mapper = { cursor ->
                    if (cursor.next().value) {
                        QueryResult.Value(cursor.getLong(0))
                    } else {
                        QueryResult.Value(null)
                    }
                },
            ).value ?: 0L

    private fun setUserSchemaVersion(
        driver: JdbcSqliteDriver,
        currentSchemaVersion: Long,
    ) = driver.execute(
        identifier = null,
        sql = "PRAGMA user_version = $currentSchemaVersion;",
        parameters = 0,
    )
}
