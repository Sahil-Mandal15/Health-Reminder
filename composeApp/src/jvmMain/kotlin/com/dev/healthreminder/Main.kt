package com.dev.healthreminder

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dev.healthreminder.database.HealthReminderDatabase
import com.dev.healthreminder.di.initKoin
import com.dev.healthreminder.presentation.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext

fun main() =
    application {
        initKoin()

        // TODO: Ensure following the best practice for warm building the db
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                GlobalContext.get().get<HealthReminderDatabase>()
            }
        }

        Window(
            onCloseRequest = ::exitApplication,
            title = "Health Reminder",
        ) {
            App()
        }
    }
