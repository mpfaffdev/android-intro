package com.example.androidintro.view.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComposeViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ComposeUiState> = MutableStateFlow(ComposeUiState.Initial)
    val uiState: StateFlow<ComposeUiState> = _uiState

    fun showLegacyViewFragment() {
        _uiState.value = ComposeUiState.ShowLegacyViewFragment
    }

}
