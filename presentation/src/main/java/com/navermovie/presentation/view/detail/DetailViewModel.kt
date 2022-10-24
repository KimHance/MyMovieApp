package com.navermovie.presentation.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Actor
import com.navermovie.entity.Movie
import com.navermovie.usecase.GetActorImageUseCase
import com.navermovie.usecase.GetYoutubeLinkIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getYoutubeLinkIdUseCase: GetYoutubeLinkIdUseCase,
    private val getActorImageUseCase: GetActorImageUseCase
) : ViewModel() {

    private val _selectedMovieLinkId = MutableSharedFlow<String>()
    val selectedMovieLinkId = _selectedMovieLinkId.asSharedFlow()

    private val _actorList = MutableStateFlow<List<Actor>?>(emptyList())
    val actorList = _actorList.asStateFlow()

    fun setYoutubeVideoId(title: String) {
        viewModelScope.launch {
            runCatching {
                getYoutubeLinkIdUseCase(title)?.let {
                    _selectedMovieLinkId.emit(it)
                }
            }
        }
    }

    fun getActorImageList(movie: Movie) {
        viewModelScope.launch {
            if (movie.actors?.size == 0) {
                _actorList.value = emptyList()
            } else {
                movie.actors?.size?.let { size ->
                    val emptyActorList = mutableListOf<Actor>().apply {
                        repeat(size) { add(Actor()) }
                    }
                    _actorList.value = emptyActorList
                    getActorImageUseCase(movie)?.let { actorList ->
                        _actorList.update { actorList }
                    }
                }
            }
        }
    }
}