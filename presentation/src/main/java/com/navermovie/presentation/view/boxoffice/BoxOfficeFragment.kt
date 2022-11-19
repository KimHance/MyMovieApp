package com.navermovie.presentation.view.boxoffice

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isGone
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
import com.navermovie.presentation.utils.NetworkManager
import com.navermovie.presentation.view.MainViewModel
import com.navermovie.presentation.view.boxoffice.adapter.BoxOfficeAdapter
import com.navermovie.presentation.view.boxoffice.adapter.BoxOfficeWeekAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BoxOfficeFragment : BaseFragment<FragmentBoxOfficeBinding>(R.layout.fragment_box_office) {

    var waitTime = 0L
    private val mainViewModel: MainViewModel by activityViewModels()
    private val boxOfficeAdapter: BoxOfficeAdapter by lazy {
        BoxOfficeAdapter(itemClickListener = { movie ->
            doOnclick(movie)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
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
        initView()
    }

    private fun initView() {
        binding.apply {
            cvDailyRefetch.setOnClickListener {
                if (!NetworkManager.checkNetworkState(requireContext())){
                    showNetworkError()
                }else{
                    mainViewModel.getDailyBoxOfficeList()
                }
            }
            cvWeeklyRefetch.setOnClickListener {
                if (!NetworkManager.checkNetworkState(requireContext())){
                    showNetworkError()
                }else{
                    mainViewModel.getWeeklyBoxOfficeList()
                }
            }
        }
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.dailyBoxOfficeUiState.collect { state ->
                        when (state) {
                            is BoxOfficeUiState.Success -> {
                                showDailyRefetchButton(true)
                                boxOfficeAdapter.submitList(state.data?.toList())
                            }
                            is BoxOfficeUiState.Loading -> {
                                showDailyRefetchButton(true)
                                boxOfficeAdapter.submitList(state.data.toList())
                            }
                            else -> {
                                showDailyRefetchButton(false)
                                boxOfficeAdapter.submitList(emptyList())
                                showError()
                            }
                        }
                    }
                }
                launch {
                    mainViewModel.weeklyBoxOfficeUiState.collect { state ->
                        when (state) {
                            is BoxOfficeUiState.Success -> {
                                showWeeklyRefetchButton(true)
                                boxOfficeWeekAdapter.submitList(state.data?.toList())
                            }
                            is BoxOfficeUiState.Loading -> {
                                showWeeklyRefetchButton(true)
                                boxOfficeWeekAdapter.submitList(state.data.toList())
                            }
                            else -> {
                                showWeeklyRefetchButton(false)
                                boxOfficeWeekAdapter.submitList(emptyList())
                                showError()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showDailyRefetchButton(state: Boolean) {
        binding.cvDailyRefetch.isGone = state
    }

    private fun showWeeklyRefetchButton(state: Boolean) {
        binding.cvWeeklyRefetch.isGone = state
    }

    private fun showError() {
        Snackbar
            .make(
                requireView(),
                getString(R.string.failed_to_get_movie),
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

    private fun showNetworkError() {
        Snackbar
            .make(
                requireView(),
                getString(R.string.network_error),
                Snackbar.LENGTH_SHORT
            )
            .show()
    }


    private fun doOnclick(movie: Movie) {
        val action = BoxOfficeFragmentDirections.actionBoxOfficeFragmentToMovieDetailFragment(
            movie
        )
        requireView().findNavController()
            .navigate(action)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - waitTime >= 1500) {
                waitTime = System.currentTimeMillis()
                Snackbar.make(
                    requireView(),
                    getString(R.string.back_pressed_text),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                requireActivity().finish()
            }
        }
    }

}