package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.dev.healthreminder.database.HealthReminderDatabase
import org.w3c.dom.Worker

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver =
        WebWorkerDriver(
            Worker(
                js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)"""),
            ),
        ).also { HealthReminderDatabase.Schema.create(it).await() }
}
