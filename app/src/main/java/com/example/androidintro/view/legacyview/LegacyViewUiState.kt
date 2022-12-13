package com.example.androidintro.view.legacyview

sealed interface LegacyViewUiState {

    object Initial : LegacyViewUiState

    object ShowComposeFragment : LegacyViewUiState

}
