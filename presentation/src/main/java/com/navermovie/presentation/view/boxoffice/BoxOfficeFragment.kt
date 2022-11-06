package com.navermovie.presentation.view.boxoffice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.navermovie.entity.Movie
import com.navermovie.presentation.R
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentBoxOfficeBinding
import com.navermovie.presentation.view.MainViewModel
import com.navermovie.presentation.view.boxoffice.adapter.BoxOfficeAdapter
import com.navermovie.presentation.view.boxoffice.adapter.BoxOfficeWeekAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BoxOfficeFragment : BaseFragment<FragmentBoxOfficeBinding>(R.layout.fragment_box_office) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val boxOfficeAdapter: BoxOfficeAdapter by lazy {
        BoxOfficeAdapter(itemClickListener = { movie ->
            doOnclick(movie)
        })
    }
    private val boxOfficeWeekAdapter: BoxOfficeWeekAdapter by lazy {
        BoxOfficeWeekAdapter(itemClickListener = { movie ->
            doOnclick(movie)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectMovieList()
    }

    private fun initRecyclerView() {
        binding.rvBoxOffice.apply {
            adapter = boxOfficeAdapter
            setAlpha(true)
            setInfinite(true)
            setIntervalRatio(0.6f)
        }
        binding.rvBoxOfficeWeek.adapter = boxOfficeWeekAdapter
    }

    private fun collectMovieList() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.dailyBoxOfficeUiState.collect { state ->
                        when (state) {
                            is BoxOfficeUiState.Success -> {
                                boxOfficeAdapter.submitList(state.data?.toList())
                            }
                            is BoxOfficeUiState.Loading -> {
                                boxOfficeAdapter.submitList(state.data.toList())
                            }
                            is BoxOfficeUiState.Error -> {
                                val errorList = mutableListOf<Movie>().apply {
                                    repeat(10) { add(Movie(isError = true)) }
                                }.toList()
                                boxOfficeAdapter.submitList(errorList)
                                Snackbar
                                    .make(
                                        requireView(),
                                        getString(R.string.failed_to_get_movie),
                                        Snackbar.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                    }
                }
                launch {
                    mainViewModel.weeklyBoxOfficeUiState.collect{ state ->
                        when (state) {
                            is BoxOfficeUiState.Success -> {
                                boxOfficeWeekAdapter.submitList(state.data?.toList())
                            }
                            is BoxOfficeUiState.Loading -> {
                                boxOfficeWeekAdapter.submitList(state.data.toList())
                            }
                            is BoxOfficeUiState.Error -> {
                                val errorList = mutableListOf<Movie>().apply {
                                    repeat(10) { add(Movie(isError = true)) }
                                }.toList()
                                boxOfficeWeekAdapter.submitList(errorList)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun doOnclick(movie: Movie) {
        val action = BoxOfficeFragmentDirections.actionBoxOfficeFragmentToMovieDetailFragment(
            movie
        )
        requireView().findNavController()
            .navigate(action)
    }
}