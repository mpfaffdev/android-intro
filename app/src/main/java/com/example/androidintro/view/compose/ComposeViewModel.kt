package com.example.androidintro.view.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidintro.domain.GetClassicalComposersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComposeViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ComposeUiState> = MutableStateFlow(ComposeUiState.Initial)
    val uiState: StateFlow<ComposeUiState> = _uiState
    private val getClassicalComposersUseCase = GetClassicalComposersUseCase()
    private var searchJob: Job? = null
    private val composers = getClassicalComposersUseCase()

    init {
        _uiState.value = ComposeUiState.ShowComposers(composers)
    }

    fun showLegacyViewFragment() {
        _uiState.value = ComposeUiState.ShowLegacyViewFragment
    }

    fun onSearchTextEntered(query: String) {
        searchJob?.cancel()
        searchJob = null
        searchJob = viewModelScope.launch(Dispatchers.Default) {
            delay(1000L)
            if (uiState.value !is ComposeUiState.ShowComposers) return@launch
            val filteredComposers = composers.filter { it.contains(query) }
            _uiState.value = ComposeUiState.ShowComposers(filteredComposers)
        }
    }

}
