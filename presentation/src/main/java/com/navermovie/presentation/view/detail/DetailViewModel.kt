package com.navermovie.presentation.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import com.navermovie.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getYoutubeLinkIdUseCase: GetYoutubeLinkIdUseCase,
    private val getActorImageUseCase: GetActorImageUseCase,
    private val getMovieArticleUseCase: GetMovieArticleUseCase,
    private val getMoviePlotUseCase: GetMoviePlotUseCase,
    private val saveActorListUseCase: SaveActorListUseCase,
    private val saveArticleListUseCase: SaveArticleListUseCase,
    private val saveMovieStoryUseCase: SaveMovieStoryUseCase
) : ViewModel() {

    private val _selectedMovieLinkId = MutableSharedFlow<String>()
    val selectedMovieLinkId = _selectedMovieLinkId.asSharedFlow()

    private val _actorList = MutableStateFlow<List<Actor>?>(emptyList())
    val actorList = _actorList.asStateFlow()

    private val _articleList = MutableStateFlow<List<Article>?>(emptyList())
    val articleList = _articleList.asStateFlow()

    private val _moviePlot = MutableStateFlow("")
    val moviePlot = _moviePlot.asStateFlow()


    fun setYoutubeVideoId(title: String) {
        viewModelScope.launch {
            getYoutubeLinkIdUseCase(title).collect { link ->
                if (link.isNullOrBlank()) {
                    _selectedMovieLinkId.emit("")
                } else {
                    _selectedMovieLinkId.emit(link)
                }
            }
        }
    }

    fun getActorImageList(movie: Movie, date: Long) {
        viewModelScope.launch {
            if (movie.actors?.size == 0) {
                _actorList.value = emptyList()
            } else {
                movie.actors?.size?.let { size ->
                    val emptyActorList = mutableListOf<Actor>().apply {
                        repeat(size) { add(Actor()) }
                    }
                    getActorImageUseCase(movie).stateIn(
                        viewModelScope,
                        SharingStarted.Lazily,
                        Pair(emptyActorList, true)
                    ).collect { data ->
                        _actorList.update { data.first }
                        if (!data.second) {
                            data.first?.let { saveActorListUseCase(movie.movieCd, it, date) }
                        }
                    }
                }
            }
        }
    }

    fun getMovieArticle(movie: Movie, date: Long) {
        viewModelScope.launch {
            val emptyArticleList = mutableListOf<Article>().apply {
                repeat(5) { add(Article()) }
            }
            getMovieArticleUseCase(movie).stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                Pair(emptyArticleList, true)
            ).collect { data ->
                _articleList.update { data.first }
                if (!data.second) {
                    data.first?.let { saveArticleListUseCase(movie.movieCd, it, date) }
                }
            }
        }
    }

    fun getMoviePlot(movie: Movie, date: Long) {
        viewModelScope.launch {
            getMoviePlotUseCase(movie).collect { data ->
                _moviePlot.value = data.first
                if (!data.second) {
                    saveMovieStoryUseCase(movie.movieCd, data.first, date)
                }
            }
        }
    }
}