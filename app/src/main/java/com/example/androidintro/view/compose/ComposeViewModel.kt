package com.example.androidintro.view.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidintro.domain.GetClassicalComposersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ANDROIDINTRO-1: Text formatting
 * As a pianist, I want to have the birthday of each composer in the list in a smaller, colored text, so the list is cleaner and easier to read.
 * Acceptance criteria:
 * - The composer's names are displayed in normal text size and color.
 * - The composer's birthdays are displayed beneath their names in smaller text.
 * - The names have black color.
 * - The birthdays have blue color.
 * - A PR is created on GitHub.
 *
 * ANDROIDINTRO-2: Favorites
 * As a pianist, I want to mark my favorite composers with a star, so I have an overview of the best ones.
 * Acceptance criteria:
 * - An outlined star icon is displayed next to each composer's info.
 * - If the star icon is clicked, it changes to filled instead of outline.
 * - The state of each icon lasts as long as the screen is visible (no persistence needed).
 * - A PR is created on GitHub.
 *
 * ANDROIDINTRO-3: Persistence
 * As a pianist, I want that my favorites selection still exists after app restart, so I can come back to the app to check my favorite composers.
 * Acceptance criteria:
 * - The list of composers is persisted on the device once
 * - Any technology/framework can be used to persist the list
 * - The favorite selection should be saved along with each composer's information
 * - The marked favorites should be still marked after app restart
 * - A PR is created on GitHub.
 *
 */
class ComposeViewModel(
    private val getClassicalComposersUseCase: GetClassicalComposersUseCase = GetClassicalComposersUseCase()
) : ViewModel() {
    private val _uiState: MutableStateFlow<ComposeUiState> = MutableStateFlow(ComposeUiState.Initial)
    val uiState: StateFlow<ComposeUiState> = _uiState
    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            getClassicalComposersUseCase()
                .collectLatest { result ->
                    _uiState.emit(ComposeUiState.ShowComposers(result))
                }
        }
    }

    fun showLegacyViewFragment() {
        _uiState.value = ComposeUiState.ShowLegacyViewFragment
    }

    fun onSearchTextEntered(query: String) {
        searchJob?.cancel()
        searchJob = null
        searchJob = viewModelScope.launch(Dispatchers.Default) {
            delay(1000L)
            _uiState.update {
                if (it is ComposeUiState.ShowComposers) {
                    it.copy(info = it.info.filter { it.name.contains(query) })
                } else {
                    it
                }
            }
        }
    }

}
