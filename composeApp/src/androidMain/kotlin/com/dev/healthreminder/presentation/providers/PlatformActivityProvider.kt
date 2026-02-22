package com.dev.healthreminder.presentation.providers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * This is a provider to provide activity to other services,
 * if we directly inject activity to services, it will leak,
 * so here we store weak reference of an activity bind by
 * its lifecycle and handled accordingly
 */
object PlatformActivityProvider : Application.ActivityLifecycleCallbacks {
    private var currentActivity: WeakReference<Activity>? = null
    val getCurrentActivity: Activity? get() = currentActivity?.get()

    override fun onActivityCreated(
        activity: Activity,
        bundle: Bundle?,
    ) {}

    override fun onActivityStarted(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivitySaveInstanceState(
        activity: Activity,
        bundle: Bundle,
    ) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }
}
