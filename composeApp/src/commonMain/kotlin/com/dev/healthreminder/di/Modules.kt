package com.dev.healthreminder.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule =
    module {
        // TODO: add dependencies here ...
    }
