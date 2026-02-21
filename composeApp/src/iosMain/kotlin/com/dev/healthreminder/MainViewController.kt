package com.dev.healthreminder

import androidx.compose.ui.window.ComposeUIViewController
import com.dev.healthreminder.presentation.ui.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
