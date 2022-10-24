package com.navermovie.presentation.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.navermovie.presentation.R
import com.navermovie.presentation.YOUTUBE
import com.navermovie.presentation.YOUTUBE_WATCH_LINK
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentMovieDetailBinding
import com.navermovie.presentation.view.detail.adapter.DetailActorAdapter
import com.navermovie.presentation.view.detail.adapter.DetailGenreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {
    private val detailViewModel: DetailViewModel by viewModels()
    private val navArgs: MovieDetailFragmentArgs by navArgs()
    private val genreAdapter: DetailGenreAdapter by lazy {
        DetailGenreAdapter()
    }
    private val actorAdapter: DetailActorAdapter by lazy {
        DetailActorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.change_bounds)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSelectedMovie()
        initView()
        collectFlow()
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
            cvDetailTeaser.setOnClickListener {
                detailViewModel.setYoutubeVideoId("${movie?.title} 티저")
            }
            rvDetailActors.adapter = actorAdapter
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailViewModel.selectedMovieLinkId.collect { id ->
                        requireActivity().startActivity(
                            Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse(YOUTUBE_WATCH_LINK + id))
                                .setPackage(YOUTUBE)
                        )
                    }
                }
                launch {
                    detailViewModel.actorList.collect { actorList ->
                        actorAdapter.submitList(actorList?.toList())
                    }
                }
            }
        }
    }

    private fun initSelectedMovie() {
        binding.movie = navArgs.movie
        detailViewModel.getActorImageList(navArgs.movie)
    }
}