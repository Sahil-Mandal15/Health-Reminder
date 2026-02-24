package com.dev.healthreminder.di

import com.dev.healthreminder.presentation.permission.NotificationPermissionManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule =
    module {
        singleOf(::NotificationPermissionManager)
    }
