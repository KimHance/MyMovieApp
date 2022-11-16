package com.navermovie.presentation.view.boxoffice

import com.navermovie.entity.Movie

sealed class BoxOfficeUiState {
    data class Success(val data: List<Movie>?) : BoxOfficeUiState()
    data class Loading(val data: List<Movie>) : BoxOfficeUiState()
    data class Empty(val query: String) : BoxOfficeUiState()

    object Error : BoxOfficeUiState()
}
