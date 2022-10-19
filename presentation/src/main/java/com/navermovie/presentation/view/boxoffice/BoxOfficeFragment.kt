package com.navermovie.presentation.view.boxoffice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.navermovie.entity.Movie
import com.navermovie.presentation.R
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentBoxOfficeBinding
import com.navermovie.presentation.view.MainViewModel
import com.navermovie.presentation.view.boxoffice.adapter.BoxOfficeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoxOfficeFragment : BaseFragment<FragmentBoxOfficeBinding>(R.layout.fragment_box_office) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val boxOfficeAdapter: BoxOfficeAdapter by lazy {
        BoxOfficeAdapter(itemClickListener = {
            doOnclick(it)
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
    }

    private fun collectMovieList() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.movieList.collect { movieList ->
                    boxOfficeAdapter.submitList(movieList.toList())
                }
            }
        }
    }

    private fun doOnclick(movie: Movie) {
        val action = BoxOfficeFragmentDirections.actionBoxOfficeFragmentToMovieDetailFragment(
            movie
        )
        requireView().findNavController().navigate(action)
    }
}