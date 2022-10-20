package com.navermovie.presentation.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.navermovie.presentation.R
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentMovieDetailBinding
import com.navermovie.presentation.view.MainViewModel
import com.navermovie.presentation.view.detail.adapter.DetailGenreAdapter

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {
    private val detailViewModel: MainViewModel by activityViewModels()
    private val navArgs: MovieDetailFragmentArgs by navArgs()
    private val genreAdapter: DetailGenreAdapter by lazy {
        DetailGenreAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSelectedMovie()
        initView()
    }

    private fun initView() {
        binding.apply {
            ivDetailBack.setOnClickListener {
                requireView().findNavController().popBackStack()
            }
            rvDetailGenre.adapter = genreAdapter
            movie?.let {
                genreAdapter.submitList(it.genres?.toList())
            }
        }
    }

    private fun initSelectedMovie() {
        binding.movie = navArgs.movie
    }
}