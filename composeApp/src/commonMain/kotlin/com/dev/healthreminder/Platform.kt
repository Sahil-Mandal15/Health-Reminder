package com.dev.healthreminder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
