package com.example.androidintro.view.legacyview

import androidx.lifecycle.ViewModel
import com.example.androidintro.domain.GetClassicalComposersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LegacyViewViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<LegacyViewUiState> = MutableStateFlow(LegacyViewUiState.Initial)
    val uiState: StateFlow<LegacyViewUiState> = _uiState

    private val getClassicalComposersUseCase = GetClassicalComposersUseCase()

    fun showComposeFragment() {
        _uiState.value = LegacyViewUiState.ShowComposeFragment
    }

}
