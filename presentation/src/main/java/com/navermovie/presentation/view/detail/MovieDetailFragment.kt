package com.navermovie.presentation.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.navermovie.presentation.R
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentMovieDetailBinding
import com.navermovie.presentation.view.MainViewModel

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {
    private val detailViewModel: MainViewModel by activityViewModels()
    private val navArgs: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSelectedMovie()
    }

    private fun initSelectedMovie() {
        detailViewModel.setSelectedMovie(navArgs.movie)
        binding.movie = navArgs.movie
    }
}