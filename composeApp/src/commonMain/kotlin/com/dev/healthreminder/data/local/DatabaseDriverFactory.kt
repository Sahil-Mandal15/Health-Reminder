package com.dev.healthreminder.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    suspend fun createDriver(): SqlDriver
}
