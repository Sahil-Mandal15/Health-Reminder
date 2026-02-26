package com.dev.healthreminder.presentation.states

sealed class PermissionUiState {
    object Granted : PermissionUiState()

    object Denied : PermissionUiState()

    object NotDetermined : PermissionUiState()
}
