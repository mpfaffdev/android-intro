package com.example.androidintro.view.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidintro.R
import com.example.androidintro.view.legacyview.LegacyViewFragment
import kotlinx.coroutines.launch

class ComposeFragment : Fragment() {

    private lateinit var viewModel: ComposeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[ComposeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeScreen(viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        ComposeUiState.Initial -> {
                            // no-op
                        }
                        is ComposeUiState.ShowComposers -> {
                            // no-op
                        }
                        ComposeUiState.ShowLegacyViewFragment -> showLegacyViewFragment()
                    }
                }
            }
        }
    }

    private fun showLegacyViewFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, LegacyViewFragment.newInstance())
            .commitNow()
    }

    companion object {
        fun newInstance() = ComposeFragment()
    }

}
