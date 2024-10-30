package com.example.androidintro.view.compose

import com.example.androidintro.domain.Composer

sealed interface ComposeUiState {

    object Initial : ComposeUiState

    object ShowLegacyViewFragment : ComposeUiState

    data class ShowComposers(
        val info: List<Composer>
    ): ComposeUiState

}
