package com.dev.healthreminder.presentation.ui

import com.dev.healthreminder.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = "Hello, ${platform.name}!"
}
