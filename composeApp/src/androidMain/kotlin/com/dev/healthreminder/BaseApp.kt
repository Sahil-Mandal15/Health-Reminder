package com.dev.healthreminder

import android.app.Application
import com.dev.healthreminder.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BaseApp)
        }
    }
}