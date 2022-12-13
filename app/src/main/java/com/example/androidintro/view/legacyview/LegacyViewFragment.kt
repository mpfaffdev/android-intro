package com.example.androidintro.view.legacyview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidintro.R
import com.example.androidintro.view.compose.ComposeFragment
import kotlinx.coroutines.launch

class LegacyViewFragment : Fragment() {

    private lateinit var viewModel: LegacyViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LegacyViewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_legacy_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectUiState()

        val navigationButton: Button = view.findViewById(R.id.fragment_legacy_view_navigation_btn)
        navigationButton.setOnClickListener { viewModel.showComposeFragment() }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        LegacyViewUiState.Initial -> {
                            // no-op
                        }
                        LegacyViewUiState.ShowComposeFragment -> showComposeFragment()
                    }
                }
            }
        }
    }

    private fun showComposeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ComposeFragment.newInstance())
            .commitNow()
    }

    companion object {
        fun newInstance() = LegacyViewFragment()
    }

}
