package com.navermovie.presentation.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navermovie.entity.Movie
import com.navermovie.usecase.GetAllBookmarkMovieUseCase
import com.navermovie.usecase.GetSearchBookmarkMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBookmarkMovieUseCase: GetAllBookmarkMovieUseCase,
    private val getSearchBookmarkMovieUseCase: GetSearchBookmarkMovieUseCase
) : ViewModel() {

    private val _bookmarkList = MutableStateFlow<List<Movie>>(emptyList())
    val bookmarkList = _bookmarkList.asStateFlow()

    fun getAllList() {
        viewModelScope.launch {
            getAllBookmarkMovieUseCase().collect { list ->
                _bookmarkList.update { list }
            }
        }
    }

    fun getSearchList(query: String) {
        viewModelScope.launch {
            getSearchBookmarkMovieUseCase(query).collect { list ->
                _bookmarkList.update { list }
            }
        }
    }
}