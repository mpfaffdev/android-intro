package com.example.androidintro.view.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComposeScreen(viewModel: ComposeViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("ComposeFragment")
        Spacer(modifier = Modifier.height(24.dp))
        if (uiState !is ComposeUiState.ShowLegacyViewFragment) {
            Button(onClick = { viewModel.showLegacyViewFragment() }) {
                Text("Go to LegacyViewFragment")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        when(val state = uiState) {
            is ComposeUiState.ShowComposers -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.info) { item ->
                        Text(item)
                    }
                }
            }
            else -> {
                // no-op
            }
        }
    }
}
