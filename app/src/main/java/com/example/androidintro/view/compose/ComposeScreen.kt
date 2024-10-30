package com.example.androidintro.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposeScreen(viewModel: ComposeViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    var currentSearchQuery by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("ComposeFragment")
        Spacer(modifier = Modifier.height(8.dp))
        if (uiState !is ComposeUiState.ShowLegacyViewFragment) {
            Button(onClick = { viewModel.showLegacyViewFragment() }) {
                Text("Go to LegacyViewFragment")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = currentSearchQuery,
            onValueChange = {
                viewModel.onSearchTextEntered(it)
                currentSearchQuery = it
            },
            label = { Text("Composer Search") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        when (val state = uiState) {
            is ComposeUiState.ShowComposers -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.info) { item ->
                        ComposerItem(
                            name = item.name,
                            birthDate = item.birthDate
                        )
                    }
                }
            }

            else -> {
                // no-op
            }
        }
    }
}

@Composable
fun ComposerItem(
    modifier: Modifier = Modifier,
    name: String,
    birthDate: String?
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp
        )
        birthDate?.let {
            Text(
                text = it,
                color = Color.Blue,
                fontSize = 12.sp
            )
        }
    }
}
