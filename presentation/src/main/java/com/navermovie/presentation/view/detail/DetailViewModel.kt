package com.navermovie.presentation.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Actor
import com.navermovie.entity.Article
import com.navermovie.entity.Movie
import com.navermovie.usecase.GetActorImageUseCase
import com.navermovie.usecase.GetMovieArticleUseCase
import com.navermovie.usecase.GetMoviePlotUseCase
import com.navermovie.usecase.GetYoutubeLinkIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getYoutubeLinkIdUseCase: GetYoutubeLinkIdUseCase,
    private val getActorImageUseCase: GetActorImageUseCase,
    private val getMovieArticleUseCase: GetMovieArticleUseCase,
    private val getMoviePlotUseCase: GetMoviePlotUseCase
) : ViewModel() {

    private val _selectedMovieLinkId = MutableSharedFlow<String>()
    val selectedMovieLinkId = _selectedMovieLinkId.asSharedFlow()

    private val _actorList = MutableStateFlow<List<Actor>?>(emptyList())
    val actorList = _actorList.asStateFlow()

    private val _articleList = MutableStateFlow<List<Article>?>(emptyList())
    val articleList = _articleList.asStateFlow()

    private val _moviePlot = MutableStateFlow<String>("")
    val moviePlot = _moviePlot.asStateFlow()


    fun setYoutubeVideoId(title: String) {
        viewModelScope.launch {
            getYoutubeLinkIdUseCase(title)?.let { link ->
                _selectedMovieLinkId.emit(link)
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
                    getActorImageUseCase(movie, date).stateIn(
                        viewModelScope,
                        SharingStarted.Lazily,
                        emptyActorList
                    ).collect { actorList ->
                        _actorList.update { actorList }
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
            getMovieArticleUseCase(movie, date).stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                emptyArticleList
            ).collect { articleList ->
                if (articleList != null) {
                    _articleList.update { articleList }
                } else {
                    _articleList.value = emptyList()
                }
            }
        }
    }

    fun getMoviePlot(movie: Movie) {
        viewModelScope.launch {
            _moviePlot.value = getMoviePlotUseCase(movie)
        }
    }
}