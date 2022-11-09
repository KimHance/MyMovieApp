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
import com.navermovie.entity.Article
import com.navermovie.presentation.*
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentMovieDetailBinding
import com.navermovie.presentation.view.detail.adapter.DetailActorAdapter
import com.navermovie.presentation.view.detail.adapter.DetailArticleAdapter
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
    private val articleAdapter: DetailArticleAdapter by lazy {
        DetailArticleAdapter(itemClickListener = { doOnClick(it) })
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
            rvDetailArticle.adapter = articleAdapter
            ivDetailShare.setOnClickListener {
                sendKakaoMessage()
            }
            ivTicket.setOnClickListener {
                TicketingBottomSheetDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    "ticketing_bottom_sheet"
                )
            }
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
                launch {
                    detailViewModel.articleList.collect { articleList ->
                        articleAdapter.submitList(articleList?.toList())
                    }
                }
                launch {
                    detailViewModel.moviePlot.collect { plot ->
                        binding.plot = plot
                    }
                }
            }
        }
    }

    private fun initSelectedMovie() {
        binding.movie = navArgs.movie
        val currentTime = System.currentTimeMillis()
        detailViewModel.apply {
            getActorImageList(navArgs.movie, currentTime)
            getMovieArticle(navArgs.movie, currentTime)
            getMoviePlot(navArgs.movie, currentTime)
        }
    }

    private fun sendKakaoMessage() {
        binding.movie?.let {
            val intent = Intent(Intent.ACTION_SEND).apply {
                setType("text/html")
                putExtra(
                    Intent.EXTRA_TEXT, "${it.title}(${it.openDate}개봉) \n\n" +
                            "예매하기 \n" +
                            "CGV : $CGV_TICKETING \n\n" +
                            "롯데시네마 : $LOTTE_CINEMA_TICKETING \n\n" +
                            "메가박스 : $MEGA_BOX_BOOKING \n"
                )
                setPackage(KAKAO_TALK)
            }
            startActivity(Intent.createChooser(intent, "${it.title}"))
        }
    }

    private fun doOnClick(item: Article) {
        requireActivity().startActivity(
            Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(item.link))
                .setPackage(CHROME)
        )
    }
}