package com.dev.healthreminder.presentation.ui.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.healthreminder.domain.model.ConfigureItemModel
import com.dev.healthreminder.domain.repository.UserConfigurationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

const val TIMEOUT = 3000L

class ConfigureViewModel(
    private val repo: UserConfigurationRepository,
) : ViewModel() {
    val reminders: StateFlow<List<ConfigureItemModel>> =
        repo
            .getAllReminders()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT),
                initialValue = emptyList(),
            )
}
