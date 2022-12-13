package com.example.androidintro.view.compose

sealed interface ComposeUiState {

    object Initial : ComposeUiState

    object ShowLegacyViewFragment : ComposeUiState

}
