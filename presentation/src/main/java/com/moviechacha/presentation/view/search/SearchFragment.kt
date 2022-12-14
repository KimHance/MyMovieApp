package com.moviechacha.presentation.view.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.moviechacha.entity.Movie
import com.moviechacha.presentation.R
import com.moviechacha.presentation.base.BaseFragment
import com.moviechacha.presentation.databinding.FragmentSearchBinding
import com.moviechacha.presentation.view.boxoffice.BoxOfficeUiState
import com.moviechacha.presentation.view.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(
            itemClickListener = { doOnclick(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
    }

    private fun initView() {
        binding.apply {
            rvSearch.adapter = searchAdapter
            etSearchTitle.setOnKeyListener { _, keyCode, event ->
                if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (etSearchTitle.text.isNotBlank()) {
                        searchViewModel.getSearchList(etSearchTitle.text.toString())
                    }
                    true
                } else {
                    false
                }
            }
            ivTitleSearch.setOnClickListener {
                if (etSearchTitle.text.isNotBlank()) {
                    searchViewModel.getSearchList(etSearchTitle.text.toString())
                }
            }
            ivSearchBack.setOnClickListener {
                requireView().findNavController().popBackStack()
            }
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.searchList.collect { state ->
                        when (state) {
                            is BoxOfficeUiState.Success -> {
                                searchAdapter.submitList(state.data?.toList())
                            }
                            is BoxOfficeUiState.Loading -> {
                                if (state.data.isNotEmpty()) {
                                    searchAdapter.submitList(state.data.toList())
                                    searchViewModel.fetchList()
                                }
                            }
                            is BoxOfficeUiState.Error -> {
                                Snackbar.make(
                                    requireView(),
                                    getString(R.string.search_error),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                searchAdapter.submitList(emptyList())
                            }
                            is BoxOfficeUiState.Empty -> {
                                searchAdapter.submitList(emptyList())
                                Snackbar.make(
                                    requireView(),
                                    getString(R.string.empty_search),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                launch {
                    searchViewModel.isSearchLoading.collect { state ->
                        binding.isLoading = state
                    }
                }
            }
        }
    }

    private fun doOnclick(movie: Movie) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                movie
            )
        requireView().findNavController()
            .navigate(action)
    }
}