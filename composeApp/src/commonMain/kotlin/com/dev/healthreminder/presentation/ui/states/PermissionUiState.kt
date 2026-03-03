package com.dev.healthreminder.presentation.ui.states

sealed class PermissionUiState {
    object Granted : PermissionUiState()

    object Denied : PermissionUiState()

    object NotDetermined : PermissionUiState()
}
